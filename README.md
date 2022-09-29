# Dicing game

## General
A game where you can spend your free time playing against bots or your friends. You can challange a friend on only one device. Playing against AI, you can choose one of 3 difficulties: easy, normal or hard.
<p align="center">
  <img src="https://github.com/viacheslav-chugunov/dicing-game/blob/master/images/menu_preview.png" width="250" >
  <b>&nbsp</b>
  <img src="https://github.com/viacheslav-chugunov/dicing-game/blob/master/images/game_preview.png" width="250" >
</p>

## Rules
In the game against AI, you always start first, in the game against a friend, the first move is randomly determined. At the start of the game, you will be notifyed which player moves first. Before starting the move, pay attention to the status bar, there you can see the points of both players and the dice, which will be installed on the next move. A dice from the status bar is placed in the playing field of the specified player. Before each move begins, the status bar dice is updated to a random value. One dice with a value of X will give X points. If on one horizontal, vertical or main diagonal (diagonal, which contains 3 cells) there are 2 dices with an X value, then you additionally get a bonus of 3 * X; if there are 3 dices, then you additionally get a 5 * X bonus. If you place a dice with a value of X on one line on which the opponent has dices with a value of X, then the dices of the opponent with a value of X on the specified line are destroyed. When one of the players occupies all the cells, the game ends. The winner is the player who received the most points at the end of the game.

Status bar preview:
<p align="start">
  <img src="https://github.com/viacheslav-chugunov/dicing-game/blob/master/images/status_bar.jpg" width="250" >
</p>

Playing field preview:
<p align="start">
  <img src="https://github.com/viacheslav-chugunov/dicing-game/blob/master/images/playing_field.jpg" width="250" >
</p>

## Licence
```
MIT License

Copyright (c) 2022 Viacheslav

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
