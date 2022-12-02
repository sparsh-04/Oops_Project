/** @type {import('tailwindcss').Config} */
module.exports = {

  content: [
      "./node_modules/flowbite/**/*.js"
  ]

}
module.exports = {
  plugins: [
    require('flowbite/plugin')
],
  content: ["*"],
  theme: {
    extend: {
      left:{
        'c' : '32rem'
      },
      
      height:{
        'b': '60vh'
      }
    },
  },
  font: {
    'nav': ['Bebas Neue '],
  },
  plugins: [],
}
