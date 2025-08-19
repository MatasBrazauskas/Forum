import { useState, useRef, useEffect } from 'react';
import { Client, type IMessage } from '@stomp/stompjs'; 

export function useWebSocketComment(){
    const [comments, setComment] = useState<string[]>([]);
    const [typing, setTyping] = useState<string[]>([]);

    const stompClientRef = useRef<Client>(null);

    useEffect(() => {
        const socket = new WebSocket('http://localhost:8080/ws');
        const client = new Client({
            webSocketFactory: () => socket,
            reconnectDelay: 5000,
            debug: (msg: string) => console.error(msg),
        })

        client.onConnect = () => {
            console.log('Connecter');

            client.subscribe('/topic/comment', (message: IMessage) => {
                setComment(prev => [...prev, message.body]);
            })

            client.subscribe('topic/typing', (message: IMessage) => {
                setTyping(prev => [...prev, message.body]);
            })

            setTimeout(() => setTyping([]), 3000);
        }

        client.activate();
        stompClientRef.current = client;

        return () => {
            client.deactivate();
        }

    }, []);

    function sendComment(text: string) {
        if (stompClientRef.current && stompClientRef.current.connected) {
            stompClientRef.current.publish({
                destination: "/app/comment",
                body: text,
            });
        }
    }

    function sendTyping(username: string) {
        if (stompClientRef.current && stompClientRef.current.connected) {
            stompClientRef.current.publish({
                destination: "/app/typing",
                body: username,
            });
        }
    }

    return { comments, typing, sendComment, sendTyping };

}