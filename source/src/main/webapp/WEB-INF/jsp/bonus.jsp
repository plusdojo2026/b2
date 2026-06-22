<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bonus.css">
        <title>ログインボーナス</title>
    </head>
    <body>
        <div>ログインボーナス</div>
        <div class="title">BINGO</div>


        <table class="bingo_table" cellspacing="20">
            <tr>
                <td style="background-color: ${bingo.day1 == true ? 'red' : 'aqua'};"><img src="${bingo.day1 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day2 == true ? 'red' : 'aqua'};"><img src="${bingo.day2 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day3 == true ? 'red' : 'aqua'};"><img src="${bingo.day3 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day4 == true ? 'red' : 'aqua'};"><img src="${bingo.day4 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day5 == true ? 'red' : 'aqua'};"><img src="${bingo.day5 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
            </tr>
            <tr>
                <td style="background-color: ${bingo.day6 == true ? 'red' : 'aqua'};"><img src="${bingo.day6 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day7 == true ? 'red' : 'aqua'};"><img src="${bingo.day7 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day8 == true ? 'red' : 'aqua'};"><img src="${bingo.day8 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day9 == true ? 'red' : 'aqua'};"><img src="${bingo.day9 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day10 == true ? 'red' : 'aqua'};"><img src="${bingo.day10 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
            </tr>
            <tr>
                <td style="background-color: ${bingo.day11 == true ? 'red' : 'aqua'};"><img src="${bingo.day11 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day12 == true ? 'red' : 'aqua'};"><img src="${bingo.day12 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day13 == true ? 'red' : 'aqua'};"><img src="${bingo.day13 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day14 == true ? 'red' : 'aqua'};"><img src="${bingo.day14 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day15 == true ? 'red' : 'aqua'};"><img src="${bingo.day15 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
            </tr>
            <tr>
                <td style="background-color: ${bingo.day16 == true ? 'red' : 'aqua'};"><img src="${bingo.day16 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day17 == true ? 'red' : 'aqua'};"><img src="${bingo.day17 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day18 == true ? 'red' : 'aqua'};"><img src="${bingo.day18 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day19 == true ? 'red' : 'aqua'};"><img src="${bingo.day19 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day20 == true ? 'red' : 'aqua'};"><img src="${bingo.day20 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
            </tr>
            <tr>
                <td style="background-color: ${bingo.day21 == true ? 'red' : 'aqua'};"><img src="${bingo.day21 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day22 == true ? 'red' : 'aqua'};"><img src="${bingo.day22 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day23 == true ? 'red' : 'aqua'};"><img src="${bingo.day23 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day24 == true ? 'red' : 'aqua'};"><img src="${bingo.day24 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
                <td style="background-color: ${bingo.day25 == true ? 'red' : 'aqua'};"><img src="${bingo.day25 == true ? 'img/memdaco.png' : 'img/Snail.png'};"></td>
            </tr>
        </table>


        <br><br>

        <div class="title">水深チェック</div>

        <div></div>

    </body>
</html>