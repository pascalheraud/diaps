const path = require('path');
const VueLoaderPlugin = require('vue-loader/lib/plugin')

module.exports = {
  entry: {
    bilan: './src/main/ts/bilan.ts',
    personnes: './src/main/ts/personnes.ts',
    bilandys: './src/main/ts/bilandys.ts',
    bilanfm: './src/main/ts/bilanfm.ts',
  },
  devtool: 'inline-source-map',
  module: {
    rules: [
      {
        test: /\.ts$/,
        loader: 'ts-loader',
        exclude: /node_modules/,
        options: {
          appendTsSuffixTo: [/\.vue$/],
        }
      },
      {
        test: /\.vue$/,
        loader: 'vue-loader'
      }
    ],
  },
  plugins: [
    new VueLoaderPlugin()
  ],
  resolve: {
    extensions: ['.vue', '.ts', '.js'],
    alias: {
      'vue$': 'vue/dist/vue.esm.js'
    }
  },
  output: {
    path: path.resolve(__dirname, 'src/main/resources/static/js/gen/bundles'),
    filename: '[name].js',
  }
};