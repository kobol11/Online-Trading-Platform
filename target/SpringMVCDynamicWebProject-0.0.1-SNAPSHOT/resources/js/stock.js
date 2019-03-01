/**
 * 
 */


'use strict';

const DEFAULT_PORTFOLIOS = [
  {'name': 'Market ETFs', 'symbols': ['SPY', 'DIA', 'QQQ', 'IWM']},
  {'name': 'Sector ETFs', 'symbols': ['XLF', 'XLK', 'XLC', 'XLV', 'XLP', 'XLY', 'XLE', 'XLB', 'XLI', 'XLU', 'XLRE']},
  {'name': 'Banks', 'symbols': ['GS', 'MS', 'JPM', 'WFC', 'C', 'BAC', 'BCS', 'DB', 'CS', 'RBS']},
  {'name': 'Tech', 'symbols': ['AAPL', 'GOOGL', 'MSFT', 'AMZN', 'FB', 'TWTR', 'NFLX', 'SNAP', 'SPOT', 'DBX', 'SQ', 'SFIX', 'BABA', 'INTC', 'AMD', 'NVDA', 'ORCL']},
  {'name': 'Cryptos', 'symbols': ['BTCUSDT', 'ETHUSDT']},
  {'name': 'Bond ETFs', 'symbols': ['BND', 'BIV', 'JNK']},
  {'name': 'Other ETFs', 'symbols': ['VOO', 'VTI', 'VGK', 'VPL', 'VWO', 'VDE', 'XOP', 'VFH', 'VHT', 'VIG', 'VYM', 'VAW', 'REM', 'XHB', 'XRT', 'GLD', 'SHV', 'FLOT', 'BKLN', 'MJ']},
  {'name': 'Mortgage REITs', 'symbols': ['EFC', 'EARN', 'NLY', 'AGNC', 'CIM', 'TWO', 'NRZ']},
  {'name': 'Autos', 'symbols': ['F', 'GM', 'FCAU', 'TM', 'HMC', 'TSLA']},
  {'name': 'Airlines', 'symbols': ['DAL', 'LUV', 'UAL', 'AAL', 'ALK', 'JBLU', 'SAVE']},
  {'name': 'BigCos', 'symbols': ['XOM', 'WMT', 'JNJ', 'GE', 'T', 'KO', 'DIS', 'MCD', 'PG']}
];

const PORTFOLIOS = DEFAULT_PORTFOLIOS;
const REFRESH_SECONDS = 10;
const BATCH_SIZE = 100;
const BASE_URL = 'https://api.iextrading.com/1.0/stock/market/batch';
const FAVICON_SYMBOL = 'SPY';
const FAVICON_BASE_URL = 'https://d3v3cbxkdlyonc.cloudfront.net/stocks';

let symbols = [FAVICON_SYMBOL];
let containerDiv = document.querySelector('.stocks-container');
let updatedDiv = document.querySelector('.updated-timestamp');

PORTFOLIOS.forEach(function(p, i){
	addPortfolio(p, i === 0); 
});
symbols = symbols.filter(function(s, i){ return symbols.indexOf(s) === i;} );
updateData('addTitle');


function addPortfolio(portfolio, includeHeader){
	 let tableHeaderHtml = '';
	 if(includeHeader){
		 tableHeaderHtml = '<thead><tr><th></th>' +
		                   '<th class="stock-price">Last</th>'+
		                   '<th class="stock-change">Change</th>'+
		                   '<th class="stock-change-pct">Change%</th>' +
		                   '<th class="stock-mkt-cap">Mkt Cap</th>' +
		                   '</tr></thead>';
	 }
	 
	 let tableBodyHtml = portfolio.symbols.map(function(symbol){
		 symbol = symbol.toUpperCase();
         symbols.push(symbol);
         let html = '';
         html = '<tr data-symbol="' + symbol + '">' +
                '<td class="stock-symbol"><a href="' + symbolUrl(symbol) + '" target="_blank">' + symbol + '</a></td>' +
                '<td class="stock-price"></td>' +
                '<td class="stock-change"></td>' +
                '<td class="stock-change-pct"></td>' +
                '<td class="stock-mkt-cap"></td>' +
	       '</tr>';
         return html;
	 }).join(' ');
	 
	 let portfolioDiv = document.createElement('div');
	 
	 portfolioDiv.innerHTML = '<details open>' +
	 						  '<summary>' +
	 						    '<h2>' + portfolio.name + '</h2>' +
	 						   '</summary>' +
	 						   '<table>' + tableHeaderHtml + '<tbody>' + tableBodyHtml + '</tbody>' + '</table>' +
	                          '</details>';
	 containerDiv.appendChild(portfolioDiv);
}

function updateData(addTitle){
	 let numberOfBatches = Math.ceil(symbols.length / BATCH_SIZE);

     for (var i = 0; i < numberOfBatches; i++) {
       let symbolsBatch = symbols.slice(i * BATCH_SIZE, (i + 1) * BATCH_SIZE);
       updateDataForBatch(symbolsBatch, addTitle);
     }
     updatedDiv.innerHTML = 'Data updated at ' + new Date().toLocaleString();
}

function updateDataForBatch(symbols, addTitle){
	let filters = ['latestPrice', 'change', 'changePercent', 'marketCap'];
    if (addTitle) filters.push('companyName');
    let url = BASE_URL + '?types=quote&symbols=' + symbols.join(',') + '&filter=' + filters.join(',');
    fetch(url).then(function(response){return response.json();}).then(function(json){
    	symbols.forEach(function(symbol){
    		let data = json[symbol];
            if (typeof(data) === 'undefined') return;
            
            let formattedPrice = formatQuote(data.quote.latestPrice);
            let formattedChange = data.quote.change.toLocaleString('en', {'minimumFractionDigits': 2});
            let formattedChangePercent = (data.quote.changePercent * 100).toFixed(1) + '%';
            let formattedMarketCap = formatMarketCap(data.quote.marketCap);
            let rgbColor = data.quote.changePercent > 0 ? '0,255,0' : '255,0,0';
            let rgbOpacity = Math.min(Math.abs(data.quote.changePercent) * 20, 1);
            
            document.querySelectorAll('[data-symbol="' + symbol + '"] .stock-price').forEach(function(e) {
                e.innerHTML = formattedPrice;
                e.setAttribute('style', 'background-color: rgba(' + rgbColor + ',' + rgbOpacity + ')');
              });

              document.querySelectorAll('[data-symbol="' + symbol + '"] .stock-change').forEach(function(e) {
                e.innerHTML = formattedChange;
                e.setAttribute('style', 'background-color: rgba(' + rgbColor + ',' + rgbOpacity + ')');
              });

              document.querySelectorAll('[data-symbol="' + symbol + '"] .stock-change-pct').forEach(function(e) {
                e.innerHTML = formattedChangePercent;
                e.setAttribute('style', 'background-color: rgba(' + rgbColor + ',' + rgbOpacity + ')');
              });

              document.querySelectorAll('[data-symbol="' + symbol + '"] .stock-mkt-cap').forEach(function(e)  {
                e.innerHTML = formattedMarketCap;
                e.setAttribute('style', 'background-color: rgba(' + rgbColor + ',' + rgbOpacity + ')');
              });

              if (addTitle) {
                document.querySelectorAll('[data-symbol="' + symbol + '"] .stock-symbol a').forEach(function(e) {
                  e.setAttribute('title', data.quote.companyName);
                });
              }
            
    	})
    });
}

function symbolUrl(symbol){
	 return 'https://iextrading.com/apps/stocks/' + symbol;
}

function formatQuote(value) {
    let options = {
      'minimumFractionDigits': 2,
      'style': 'currency',
      'currency': 'USD'
    };
    return value.toLocaleString('en', options);
  }

  function formatMarketCap(marketCap) {
    if (marketCap === null) return '';

    let value, suffix;
    if (marketCap >= 1e12) {
      value = marketCap / 1e12;
      suffix = 'T';
    } else if (marketCap >= 1e9) {
      value = marketCap / 1e9;
      suffix = 'B';
    } else {
      value = marketCap / 1e6;
      suffix = 'M';
    }

    let digits = value < 10 ? 1 : 0;

    return '$' + value.toFixed(digits) + suffix;
  }


	PORTFOLIOS.forEach(function(p, i){ addPortfolio(p, i === 0);});
    symbols = symbols.filter(function(s, i){ return symbols.indexOf(s) === i;});
    updateData('addTitle');
    setInterval(updateData, REFRESH_SECONDS * 1000);