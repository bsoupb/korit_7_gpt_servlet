<%@ page import="java.util.List" %>
<%@ page import="com.korit.servlet_study.entity.Category" %>
<%@ page import="com.korit.servlet_study.datas.DataList" %><%--
  Created by IntelliJ IDEA.
  User: qotn0
  Date: 2025-01-06
  Time: 오전 9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${serverName}</title>
</head>
<body>
    <h1>상품등록</h1>
    <form action="http://localhost:8080/servlet_study_war/product/register" method="post">
        <table>
            <tr>
                <td>카테고리</td>
                <td>
                    <select name="category">
                        <!--
                            [EL 표현식]
                            el 표현식은 라이브러리 필요
                            표현방식 : $ {};
                            EL 표현식을 사용할 수 있는 경우
                            1. Attribute에 포함 되어 있는 값(Application, Session, Request)
                            2. JSP의 기본 설정 값
                        -->

                        <!-- for(Category category : categories) -->
                        <c:forEach var="category" items="${categories}">
                            <option value=${category.id}>${category.name}</option>
                        </c:forEach>

                    </select>
                </td>
            </tr>
            <tr>
                <td>상품명</td>
                <td><input type="text" name="productName"></td>
            </tr>
            <tr>
                <td>가격</td>
                <td><input type="text" name="price"></td>
            </tr>
            <tr>
                <td>등록일자</td>
                <td><input type="date" name="registerDate"></td>
            </tr>
        </table>
        <button type="submit">저장</button>
    </form>
</body>
</html>
