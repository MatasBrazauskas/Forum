import { useState, useRef, useEffect } from 'react';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client/dist/sockjs';

import { type AddCommentDTO } from '../Utils/RequestDTOs';

export function useWebSocketComment() {
    const [typing, setTyping] = useState<string>('');

    const stompClientRef = useRef<Client>(null);
    const typingTimeoutRef = useRef<any>(null);

    useEffect(() => {
        const socket = new SockJS('http://localhost:8080/ws');

        const client = new Client({
            webSocketFactory: () => socket,
            connectHeaders: {
                Authorization: 'matasbrazauskas123456@gmail.com'
            },
            reconnectDelay: 5000,
            debug: (msg) => console.log(msg),
        });

        client.onConnect = () => {
            client.subscribe('/topic/comments', () => {});

            client.subscribe('/topic/typing', (message) => {
                setTyping(message?.body);

                if (typingTimeoutRef.current) {
                    clearTimeout(typingTimeoutRef.current);
                }
                typingTimeoutRef.current = setTimeout(() => {
                setTyping('');
            }, 3000);
            });
        };

        client.onStompError = (frame) => {
            console.error('Broker reported error: ' + frame.headers['message']);
            console.error('Additional details: ' + frame.body);
        };

        client.activate();
        stompClientRef.current = client;

        return () => {
            if (stompClientRef.current) {
                stompClientRef.current.deactivate();
            }
        };

    }, []);

    function sendComment(obj: AddCommentDTO) {
        console.warn('Clicked')
        if (stompClientRef.current && stompClientRef.current.connected) {
            stompClientRef.current.publish({
                destination: "/app/comment",
                body: JSON.stringify(obj),
            });
        }
    }

    function sendTyping(username: string) {
        if (stompClientRef.current && stompClientRef.current.connected) {
            stompClientRef.current.publish({
                destination: "/app/typing",
                body: username,
                headers: { 
                    "content-type": "text/plain", 
                    Authorization: "matasbrazauskas123456@gmail.com" 
                },
            });
        }
    }

    return { typing, sendComment, sendTyping };
}