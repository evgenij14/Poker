<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
<%@include file='/WEB-INF/views/css/main.css' %>
</style>
<head>
    <c:if test="${isInTable || isInGame}">
    <meta http-equiv="Refresh" content="2" />
    </c:if>
    <title>Main page</title>
</head>
<body>
    <div class="back">
        <div class="header clearfix">
            <h1>Welcome, ${user.firstName} ${user.lastName}!</h1>
            <form class="logout" action="/poker/logout/" method="POST">
                <button type="submit">Logout</button>
            </form>
        </div>
        <div class="info">
            <p>Login <span>${user.login}</span></p>
            <p>Games <span>${user.games}</span></p>
            <p>Wins/loses <span>${user.wins}/${user.loses}</span></p>
            <p>WinRate <span>${user.winRate}</span></p>
            <p>Money <span>${user.money}</span></p>
            <p class="money">Money on next game ${user.moneyInGame}</p>
            <c:if test="${!isInTable}">
            <form action="/poker/main/money" method="POST">
                <input type="number" name="money" min="100" max="${user.money}" value="1000">
                <button type="submit">OK</button>
            </form>
            </c:if>
        </div>
            <div class="tables">
                <div class="table1">
                    <h2>Table 1</h2>
                    <p class="cost1">50/25$</p>
                    <p class="queue1">Players in queue ${t1}/2</p>
                    <img src="http://poker-lifestyle.ru/img/images/page_pictures/fishki_karti_pokernij_stol.jpg" alt="Table 1">
                    <c:if test="${table == 1}">
                        <form class="play" action="/poker/main/delete" method="POST">
                            <button type="submit">Leave</button>
                        </form>
                    </c:if>
                        <c:if test="${!isInTable && user.moneyInGame != 0}">
                            <form class="play" action="/poker/main/table1" method="POST">
                                <button type="submit">Play</button>
                            </form>
                        </c:if>
                </div>
                <div class="table2">
                    <h2>Table 2</h2>
                    <p class="cost2">100/50$</p>
                    <p class="queue2">Players in queue ${t2}/2</p>
                    <img src="http://poker-lifestyle.ru/img/images/page_pictures/fishki_karti_pokernij_stol.jpg" alt="Table 2">
                        <c:if test="${!isInTable  && user.moneyInGame != 0}">
                            <form class="play" action="/poker/main/table2" method="POST">
                                <button type="submit">Play</button>
                            </form>
                        </c:if>
                    <c:if test="${table == 2}">
                        <form class="play" action="/poker/main/delete" method="POST">
                            <button type="submit">Leave</button>
                        </form>
                    </c:if>
                </div>
                <div class="table3">
                    <h2>Table 3</h2>
                    <p class="cost3">200/100$</p>
                    <p class="queue3">Players in queue ${t3}/2</p>
                    <img src="http://poker-lifestyle.ru/img/images/page_pictures/fishki_karti_pokernij_stol.jpg" alt="Table 3">
                    <c:if test="${!isInTable  && user.moneyInGame != 0}">
                        <form class="play" action="/poker/main/table3" method="POST">
                            <button type="submit">Play</button>
                        </form>
                    </c:if>
                    <c:if test="${table == 3}">
                        <form class="play" action="/poker/main/delete" method="POST">
                            <button type="submit">Leave</button>
                        </form>
                    </c:if>
                </div>
            </div>
    </div>
</body>
</html>
