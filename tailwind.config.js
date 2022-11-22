/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [  './components/**/*.{html,js}',
  './pages/**/*.{html,js}',
  './index.html',
  './src/**/*.js',
  './node_modules/@my-company/tailwind-components/**/*.js',
],
  theme: {
    extend: {
      width:{
        'a' : '30vw'
      },
      height:{
        'b': '60vh'
      }
    },
  },
  plugins: [],
}
