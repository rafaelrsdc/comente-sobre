const express = require('express');
const app = express();
const PORT = 8080;

// Configuração do EJS como view engine
app.set('view engine', 'ejs');
app.set('views', __dirname + '/views');

// Armazenamento dos comentários
const comments = {};

// Rota para redirecionar para a página /comente-sobre
app.get('/', (req, res) => {
  res.redirect('/comente-sobre');
});

// Rota para a página inicial
app.get('/comente-sobre', (req, res) => {
  const { assunto } = req.query;
  if (assunto) {
    res.redirect(`/comente-sobre/${encodeURIComponent(assunto)}`);
  } else {
    res.render('index');
  }
});

// Rota para exibir os comentários de um assunto
app.get('/comente-sobre/:assunto', (req, res) => {
  const { assunto } = req.params;
  const commentList = comments[assunto] || [];
  res.render('comentarios', { assunto, commentList });
});

// Rota para adicionar um novo comentário
app.post('/comente-sobre/:assunto', express.urlencoded({ extended: true }), (req, res) => {
  const { assunto } = req.params;
  const { email, comentario } = req.body;

  if (!comments[assunto]) {
    comments[assunto] = [];
  }

  comments[assunto].push({ email, comentario });
  res.redirect(`/comente-sobre/${encodeURIComponent(assunto)}`);
});

// Inicialização do servidor
app.listen(PORT, () => {
  console.log(`Servidor rodando em http://localhost:${PORT}`);
});
