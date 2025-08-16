import { defineConfig } from 'vite'
import tailwindcss from '@tailwindcss/vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(), tailwindcss()],
  css: {
    // Add this block to tell Vite to use PostCSS
    postcss: {
      // You can add PostCSS plugins here, like autoprefixer
      plugins: [],
    },
  },
})
