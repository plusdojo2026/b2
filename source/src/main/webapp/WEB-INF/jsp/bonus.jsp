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
                <td style="background-color: ${bingo.day1 == true ? 'red' : 'aqua'};">1</td>
                <td style="background-color: ${bingo.day2 == true ? 'red' : 'aqua'};">2</td>
                <td style="background-color: ${bingo.day3 == true ? 'red' : 'aqua'};">3</td>
                <td style="background-color: ${bingo.day4 == true ? 'red' : 'aqua'};">4</td>
                <td style="background-color: ${bingo.day5 == true ? 'red' : 'aqua'};">5</td>
            </tr>
            <tr>
                <td style="background-color: ${bingo.day6 == true ? 'red' : 'aqua'};">6</td>
                <td style="background-color: ${bingo.day7 == true ? 'red' : 'aqua'};">7</td>
                <td style="background-color: ${bingo.day8 == true ? 'red' : 'aqua'};">8</td>
                <td style="background-color: ${bingo.day9 == true ? 'red' : 'aqua'};">9</td>
                <td style="background-color: ${bingo.day10 == true ? 'red' : 'aqua'};">10</td>
            </tr>
            <tr>
                <td style="background-color: ${bingo.day11 == true ? 'red' : 'aqua'};">11</td>
                <td style="background-color: ${bingo.day12 == true ? 'red' : 'aqua'};">12</td>
                <td style="background-color: ${bingo.day13 == true ? 'red' : 'aqua'};">13</td>
                <td style="background-color: ${bingo.day14 == true ? 'red' : 'aqua'};">14</td>
                <td style="background-color: ${bingo.day15 == true ? 'red' : 'aqua'};">15</td>
            </tr>
            <tr>
                <td style="background-color: ${bingo.day16 == true ? 'red' : 'aqua'};">16</td>
                <td style="background-color: ${bingo.day17 == true ? 'red' : 'aqua'};">17</td>
                <td style="background-color: ${bingo.day18 == true ? 'red' : 'aqua'};">18</td>
                <td style="background-color: ${bingo.day19 == true ? 'red' : 'aqua'};">19</td>
                <td style="background-color: ${bingo.day20 == true ? 'red' : 'aqua'};">20</td>
            </tr>
            <tr>
                <td style="background-color: ${bingo.day21 == true ? 'red' : 'aqua'};">21</td>
                <td style="background-color: ${bingo.day22 == true ? 'red' : 'aqua'};">22</td>
                <td style="background-color: ${bingo.day23 == true ? 'red' : 'aqua'};">23</td>
                <td style="background-color: ${bingo.day24 == true ? 'red' : 'aqua'};">24</td>
                <td style="background-color: ${bingo.day25 == true ? 'red' : 'aqua'};">25</td>
            </tr>
        </table>


        <br><br>

        <div class="title">水深チェック</div>

        <div></div>

    </body>
</html>