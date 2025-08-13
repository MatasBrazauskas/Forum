import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'

import { GOOGLE_CLIENT_ID } from './Login/const'
import { GoogleOAuthProvider } from '@react-oauth/google'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

import { Provider } from 'react-redux';
import store from './Store/store';

import TopBar from './TopBar/TopBar';

import './mainStyle.css';

const queryClient = new QueryClient({
  defaultOptions:{
    queries:{
      staleTime:  60 * 5,
    }
  }
});

// eslint-disable-next-line react-refresh/only-export-components
function App(){

    return (
      <div className = 'mainContainer'>
        <TopBar />
      </div>
    )
}

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <GoogleOAuthProvider clientId={GOOGLE_CLIENT_ID}>
      <QueryClientProvider client={queryClient}>
        <Provider store={store}>
          <App />
        </Provider>
      </QueryClientProvider>
    </GoogleOAuthProvider>
  </StrictMode>
);
