<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<body>
<h2>文件上传!</h2>
<form action="/user/import/v1" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="file">
    <input type="submit" value="提交">
</form>
<form action="/user/export/v1" method="post">
    <input type="submit" value="导出">
</form>

<form action="/user/exportUserAndCity/v1" method="post">
    <input type="submit" value="导出多sheet">
</form>

<form action="/user/upload/v1" method="post" enctype="multipart/form-data">
    选择文件:<input type="file" name="fileList" multiple/>
    <input type="submit" value="上传多个文件">
</form>
</body>
</html>
