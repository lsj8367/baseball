## Problems

### 문자열의 처음 2개 위치에서 A를 자릅니다.

- "ABCD" => "BCD", "AACD"=> "CD", "BACD"=>"BCD", "AAAA" => "AA", "MNAA"=>"MNAA"

### 문자열의 처음 두 문자와 마지막 두 문자가 동일한지 확인합니다.

- ""=>false, "A"=>false, "AB"=>true, "ABC"=>false, "AAA"=>true, "ABCAB"=>true, "ABCDEBA"=>false

Hint : substring method.

### Bowling Kata

Popular kata popularized by Uncle Bob

- http://butunclebob.com/ArticleS.UncleBob.TheBowlingGameKata

- 10 Bowling Pins
- Each Game has 10 frames
- Each frame has upto 2 rolls
    - Base score is sum of pins knocked down in 2 rolls
- Bonus Points
    - Spare
        - You knock down all 10 pins in your 2 rolls
        - You get double points for your next roll
    - Strike
        - You knock all 10 pins in your first roll
        - You get double points for your next two rolls
    - In 10th frame, you can have three rolls if you roll a Spare or a Strike

- Example 1 : 2,2|4,5|4,5|4,5|2,5
    - Score = 2+2+4+5+4+5+4+5+2+5
- Example 2 : 5,5|4,6|3, 7|6,4|1,1
    - Score = 5+9+4+9+3+13+6+5+1+1
- Example 3 : 10|10|10|10|1,1
    - Score = 30+30+21+12+2

#### Design

- Create a class Game with methods `void roll(int pins)` and `int score()`
