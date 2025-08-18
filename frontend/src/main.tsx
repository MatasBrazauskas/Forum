import { lazy } from 'react'
import { createRoot } from 'react-dom/client'

import { GOOGLE_CLIENT_ID } from './TopBar/Login/const';
import { GoogleOAuthProvider } from '@react-oauth/google'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import { Provider } from 'react-redux';
import store from './Store/store';

import './mainStyle.css';

export const queryClient = new QueryClient({
  defaultOptions:{
    queries:{
      staleTime:  60 * 5,
    }
  }
});

const MainPage = lazy(() => import('./MainPage'));
const ForumPage = lazy(() => import('./Forum/ForumPage'));
const ThreadPage = lazy(() => import('./Treads/ThreadPage'));
const PostPage = lazy(() => import('./Post/PostPage'));
const ProfilePage = lazy(() => import('./Profile/ProfileModal'));
const InboxPage = lazy(() => import('./TopBar/InboxPage'));

function App(){

    return (
      <div className = 'mainContainer'>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<MainPage />}>
              <Route path='' element={<ForumPage />}/>
              <Route path='threads/rules' element={<ThreadPage />}/>
              <Route path='threads/:topicsName' element={<ThreadPage />} />
              <Route path='posts/:threadsName' element={<PostPage />} />
              <Route path='profile' element={<ProfilePage />}/>
              <Route path='inbox' element={<InboxPage />}/>
            </Route>
          </Routes>
        </BrowserRouter>
      </div>
    )
}

createRoot(document.getElementById('root')!).render(
    <GoogleOAuthProvider clientId={GOOGLE_CLIENT_ID}>
      <QueryClientProvider client={queryClient}>
        <Provider store={store}>
          <App />
        </Provider>
      </QueryClientProvider>
    </GoogleOAuthProvider>
);
