﻿# プログラムの概要
ナンプレを解くプログラムです。
盤面に数字を入力しプログラムを動かすとそのナンプレの答えが出力されます。

# 使い方
## 基本操作
Eclipseから実行したり、、実行可能jarファイルとしてエクスポートして実行するなど、
何らかの方法で実行するとナンプレの盤面(9×9)が表示されます。
初期状態では通常のナンプレを解くモードになっています。

盤面上の各マスをクリックするとマスが灰色になり、選択された状態になります。
選択されたマスに数字を入れるには盤面上部の数字ボタンを使います。
マスを空白にするときは一番左のボタンを使います。
このようにしてナンプレの盤面を入力します。
盤面を入力したあとOKボタンを押すとそのナンプレを解いた結果が標準出力に出力されます。

## 問題の種類選択
盤面の右にあるラジオボタンで問題の種類を選択できます。
先述の通り初期状態は通常のナンプレ(Basic)です。

* Basic    : 通常のナンプレです。
* Diagnoal : 対角線上のマスにも各数字が一つずつ入るようにするという条件が追加されたナンプレです。
             これを選択すると対角線上のマスには斜めに線が引かれます。
* Even-Odd : 特定のマスには偶数のみ、あるいは奇数のみが入るようにするという条件が追加されたナンプレです。
             奇数か偶数かは各問題で異なります。これを選択すると盤面上部のE-Oボタンが使えるようになります。
             E-Oボタンを押すことで偶数のみ、奇数のみが入るマスを指定します。
             指定されたマスは緑色になります。
             ただしMini盤面では選択できません。

## 答えの表示
ラジオボタン群の右にあるチェックボックスにチェックを入れると、答えが二つ以上あるときに
それらをすべて出力します。
もちろん、正しいパズルとしてのナンプレは答えが一つですが、
自分で作った問題に答えが二つ以上ないことを確認するのに使えるかもしれません。

## 盤面の種類
ウィンドウ上部のメニューにあるBoardを押すとBasicかMiniかを選択できます。
Basicは通常の9×9の盤面、Miniは6×6の盤面になります。

# そのほか
* Zigzagは、通常のナンプレにある3×3の固まりを、任意の連続した9マスにしたナンプレですがまだ実装していません。
　
