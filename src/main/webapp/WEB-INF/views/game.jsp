<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    <%@include file='/WEB-INF/views/css/game.css' %>
</style>
<head>
    <c:if test="${loginUser.equals(game.player1) && game.firstMove && !game.firstWin && !game.secondWin}">
        <meta http-equiv="Refresh" content="2"/>
    </c:if>
    <c:if test="${loginUser.equals(game.player2) && game.secondMove && !game.firstWin && !game.secondWin}">
        <meta http-equiv="Refresh" content="2"/>
    </c:if>
    <title>Only win!</title>
</head>
<body>
<div class="back">
    <div class="opponent">
        <div class="money">
            <c:if test="${loginUser.equals(game.player1)}">
                <p>Money ${game.player2.moneyInGame}</p>
            </c:if>
            <c:if test="${loginUser.equals(game.player2)}">
                <p>Money ${game.player1.moneyInGame}</p>
            </c:if>
        </div>
        <div class="opponent_card">
            <c:if test="${game.numOfMove < 4}">
                <img src="https://nashpoker.net/alldata/images/rubashka__obratnaya_storona_igralnoy_karti.jpg"
                     alt="Card">
                <img src="https://nashpoker.net/alldata/images/rubashka__obratnaya_storona_igralnoy_karti.jpg"
                     alt="Card">
            </c:if>
            <c:if test="${game.numOfMove >= 4 && loginUser.equals(game.player1)}">
                <c:forEach var="card" items="${game.cards2}">
                    <img src="${card.reference}">
                </c:forEach>
            </c:if>
            <c:if test="${game.numOfMove >= 4 && loginUser.equals(game.player2)}">
                <c:forEach var="card" items="${game.cards1}">
                    <img src="${card.reference}">
                </c:forEach>
            </c:if>
        </div>
        <div class="opponent_info">
            <c:if test="${loginUser.equals(game.player1)}">
                <p>Name ${game.player2.firstName}</p>
                <p>Games ${game.player2.games}</p>
                <p>Winrate ${game.player2.winRate}%</p>
            </c:if>
            <c:if test="${loginUser.equals(game.player2)}">
                <p>Name ${game.player1.firstName}</p>
                <p>Games ${game.player1.games}</p>
                <p>Winrate ${game.player1.winRate}%</p>
            </c:if>
        </div>
    </div>
    <div class="rate1 clearfix">
        <c:if test="${loginUser.equals(game.player1)}">
            <p>Rate ${game.rate2}</p>
        </c:if>
        <c:if test="${loginUser.equals(game.player2)}">
            <p>Rate ${game.rate1}</p>
        </c:if>
    </div>
    <div class="cards clearfix">
        <img src="https://nashpoker.net/alldata/images/rubashka__obratnaya_storona_igralnoy_karti.jpg" alt="Card"
             class="deck">
        <div class="on_table">
            <c:if test="${game.numOfMove > 0}">
                <img src="${game.onTable.get(0).reference}" alt="Card">
                <img src="${game.onTable.get(1).reference}" alt="Card">
                <img src="${game.onTable.get(2).reference}" alt="Card">
            </c:if>
            <c:if test="${game.numOfMove > 1}">
                <img src="${game.onTable.get(3).reference}" alt="Card">
            </c:if>
            <c:if test="${game.numOfMove > 2}">
                <img src="${game.onTable.get(4).reference}" alt="Card">
            </c:if>
        </div>
        <div class="bank">
            <img src="https://st2.depositphotos.com/2789065/6722/i/950/depositphotos_67226577-stock-photo-poker-chips-background.jpg"
                 alt="Card">
            <p class="bank_money">Bank ${game.bank}</p>
        </div>
    </div>
    <c:if test="${param.lowRate != null}">
        <div class="low_rate">
            <p>Low rate for this table. Please, increase your rate!</p>
        </div>
    </c:if>
    <c:if test="${loginUser.equals(game.player1) && game.firstWin}">
        <div class="win">
            <p>Congratulations! You won! Your combination is ${game.data1.combinations.name()}</p>
            <form action="/poker/game/calculating" method="POST">
                <button type="submit">Return to main page</button>
            </form>
        </div>
    </c:if>
    <c:if test="${loginUser.equals(game.player2) && game.secondWin}">
        <div class="win">
            <p>Congratulations! You won! Your combination is ${game.data2.combinations.name()}</p>
            <form action="/poker/game/calculating" method="POST">
                <button type="submit">Return to main page</button>
            </form>
        </div>
    </c:if>
    <c:if test="${loginUser.equals(game.player1) && game.secondWin && !game.firstWin}">
        <div class="lose">
            <p>Sorry, but you lost ... Your combination is ${game.data1.combinations.name()}</p>
            <form action="/poker/game/calculating" method="POST">
                <button type="submit">Return to main page</button>
            </form>
        </div>
    </c:if>
    <c:if test="${loginUser.equals(game.player2) && game.firstWin && !game.secondWin}">
        <div class="lose">
            <p>Sorry, but you lost ... Your combination is ${game.data2.combinations.name()}</p>
            <form action="/poker/game/calculating" method="POST">
                <button type="submit">Return to main page</button>
            </form>
        </div>
    </c:if>
    <div class="rate2 clearfix">
        <c:if test="${loginUser.equals(game.player1)}">
            <p>Rate ${game.rate1}</p>
        </c:if>
        <c:if test="${loginUser.equals(game.player2)}">
            <p>Rate ${game.rate2}</p>
        </c:if>
    </div>
    <div class="buttons clearfix">
        <c:if test="${loginUser.equals(game.player1) && !game.firstMove}">
            <form action="/poker/game/pass" method="POST">
                <button>Pass</button>
            </form>
            <c:if test="${game.player2.moneyInGame > 0}">
                <form action="/poker/game/move" method="POST">
                    <button>Move</button>
                </form>
            </c:if>
            <c:if test="${game.numOfMove > 0}">
                <form action="/poker/game/check" method="POST">
                    <button>Check</button>
                </form>
            </c:if>
            <form action="/poker/game/allin" method="POST">
                <button>All in</button>
            </form>
        </c:if>
        <c:if test="${loginUser.equals(game.player2) && !game.secondMove}">
            <form action="/poker/game/pass" method="POST">
                <button>Pass</button>
            </form>
            <c:if test="${game.player1.moneyInGame > 0}">
                <form action="/poker/game/move" method="POST">
                    <button>Move</button>
                </form>
            </c:if>
            <c:if test="${game.numOfMove > 0}">
                <form action="/poker/game/check" method="POST">
                    <button>Check</button>
                </form>
            </c:if>
            <form action="/poker/game/allin" method="POST">
                <button>All in</button>
            </form>
        </c:if>
    </div>

    <div class="me">
        <div class="me_info">
            <c:if test="${loginUser.equals(game.player1)}">
                <p>Name ${game.player1.firstName}</p>
                <p>Games ${game.player1.games}</p>
                <p>Winrate ${game.player1.winRate}%</p>
            </c:if>
            <c:if test="${loginUser.equals(game.player2)}">
                <p>Name ${game.player2.firstName}</p>
                <p>Games ${game.player2.games}</p>
                <p>Winrate ${game.player2.winRate}%</p>
            </c:if>
        </div>
        <div class="me_money">
            <c:if test="${loginUser.equals(game.player1)}">
                <p>Money ${game.player1.moneyInGame}</p>
            </c:if>
            <c:if test="${loginUser.equals(game.player2)}">
                <p>Money ${game.player2.moneyInGame}</p>
            </c:if>
        </div>
        <div class="me_card">
            <c:if test="${loginUser.equals(game.player1)}">
                <img src="${game.cards1.get(0).reference}" alt="Card">
                <img src="${game.cards1.get(1).reference}" alt="Card">
            </c:if>
            <c:if test="${loginUser.equals(game.player2)}">
                <img src="${game.cards2.get(0).reference}" alt="Card">
                <img src="${game.cards2.get(1).reference}" alt="Card">
            </c:if>
        </div>
        <div class="chip">
            <div class="rate">
                <form action="/poker/game/rate25" method="POST">
                    <img src="https://i.ibb.co/G7qs32q/2500000.png">
                    <c:if test="${loginUser.equals(game.player1) && !game.firstMove}">
                        <button type="submit">Rate</button>
                    </c:if>
                    <c:if test="${loginUser.equals(game.player2) && !game.secondMove}">
                        <button type="submit">Rate</button>
                    </c:if>
                </form>
            </div>
            <div class="rate">
                <form action="/poker/game/rate50" method="POST">
                    <img src="https://i.ibb.co/ZSXjWhz/050.png">
                    <c:if test="${loginUser.equals(game.player1) && !game.firstMove}">
                        <button type="submit">Rate</button>
                    </c:if>
                    <c:if test="${loginUser.equals(game.player2) && !game.secondMove}">
                        <button type="submit">Rate</button>
                    </c:if>
                </form>
            </div>
            <div class="rate">
                <form action="/poker/game/rate100" method="POST">
                    <img src="https://i.ibb.co/0Xmstt8/01000.png">
                    <c:if test="${loginUser.equals(game.player1) && !game.firstMove}">
                        <button type="submit">Rate</button>
                    </c:if>
                    <c:if test="${loginUser.equals(game.player2) && !game.secondMove}">
                        <button type="submit">Rate</button>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
