const path = require('path');

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
        use: 'ts-loader',
        exclude: /node_modules/,
      },
    ],
  },
  resolve: {
    extensions: [ '.ts', '.js' ],
    alias: {
      'vue$': 'vue/dist/vue.esm.js'
    }
  },
  output: {
    path: path.resolve(__dirname, 'src/main/resources/static/js/gen/bundles'),
    filename: '[name].js',
  }
};