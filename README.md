# numberplace
## 概要
ナンプレを解くプログラムです。  
盤面に数字を入力しプログラムを動かすとそのナンプレの答えが  
出力されます。

## 使い方
Sudoku.javaを実行するとナンプレの盤面(9×9)が表示されます。 
盤面右のラジオボタンで問題の種類を選択します。各問題につい  
ては後述します。

盤面上の各マスをクリックするとマスが灰色になり、選択された  
状態になります。選択されたマスに数字を入れるには盤面上部の  
１〜９の数字ボタンを使います。マスを空白にするときは一番左  
のボタンを使います。このようにしてナンプレの盤面を入力しま  
す。盤面を入力したあとOKボタンを押すとそのナンプレを解いた  
結果が標準出力に出力されます。  

## 問題の種類
* Basic    : 通常のナンプレです。  
* Diagnoal : 対角線上のマスにも各数字が一つずつ入るようにするという条件が追加されたナンプレです。
これを選択すると対角線上のマスには斜めに線が引かれます。
* Even-Odd : 特定のマスには偶数のみ、あるいは奇数のみが入るようにするという条件が追加されたナンプレです。
奇数か偶数かは各問題で異なります。これを選択すると盤面上部のE-Oボタンが使えるようになります。
このボタンを押すと選択されたマスが緑色になります。
このようにして偶数のみ、奇数のみが入るマスを指定します。  
ただしMini盤面では選択できません。

## 盤面の種類
ウィンドウ上部のメニューにあるBoardを押すとBasicかMiniを選択できます。Basicは通常の9×9の盤面、
Miniは6×6の盤面になります。

## そのほか
* Zigzagは、通常のナンプレにある3×3の固まりを、任意の連続した9マスにしたナンプレですがまだ実装していません。
* 答えが二つ以上ある場合はすべて表示されます。
　
