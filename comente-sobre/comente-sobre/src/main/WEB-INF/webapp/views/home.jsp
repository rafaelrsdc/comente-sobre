<!DOCTYPE html>
<html>
<head>
    <title>Comente Sobre</title>
</head>
<body>
    <h1>Digite o tópico sobre o qual você deseja comentar:</h1>
    <form action="/comment" method="post">
        <input type="text" name="topic" required>
        <br>
        <input type="email" name="email" placeholder="Digite seu email" required>
        <br>
        <textarea name="comment" placeholder="Digite seu comentário" required></textarea>
        <br>
        <input type="submit" value="Enviar">
    </form>
</body>
</html>
