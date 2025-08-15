import { lazy, StrictMode } from 'react'
import { createRoot } from 'react-dom/client'

import { GOOGLE_CLIENT_ID } from './Login/const'
import { GoogleOAuthProvider } from '@react-oauth/google'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import { Provider } from 'react-redux';
import store from './Store/store';

import './mainStyle.css';

const queryClient = new QueryClient({
  defaultOptions:{
    queries:{
      staleTime:  60 * 5,
    }
  }
});

const MainPage = lazy(() => import('./Pages/MainPage'));
const ForumPage = lazy(() => import('./Forum/ForumPage'));
const RulesPage = lazy(() => import('./Rules/RulesPage'));
const ProfilePage = lazy(() => import('./Profile/ProfilePage'));
const InboxPage = lazy(() => import('./Inbox/InboxPage'));

function App(){

    return (
      <div className = 'mainContainer'>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<MainPage />}>
              <Route path='forum' element={<ForumPage/>}/>
              <Route path='rules' element={<RulesPage />}/>
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
