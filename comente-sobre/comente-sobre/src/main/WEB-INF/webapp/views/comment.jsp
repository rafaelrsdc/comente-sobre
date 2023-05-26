<!DOCTYPE html>
<html>
<head>
    <title>Comentários</title>
</head>
<body>
    <h1>Comentários sobre o tópico: ${topic}</h1>
    <ul>
        <c:forEach var="comment" items="${comments}">
            <li>Email: ${comment.email}</li>
            <li>Comentário: ${comment.comment}</li>
            <br>
        </c:forEach>
    </ul>
</body>
</html>
