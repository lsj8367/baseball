# 문자열 강조 TDD

문자열 목록에서 note가 포함된 문자열을 강조해서 출력하는 프로그램을 만든다.
단 정확하게 원하는 알파벳 만으로 이루어진 단어만을 확인해야한다.
예를 들어 note1이나 keynote는 원하는 단어가 아니다.
note의 앞이나 뒤에 숫자나 알파벳이 나오면 다른 단어라고 판단한다.
출력 결과는 note 단어를 강조하기 위해 단어의 앞뒤에 중괄호 {} 를 한다.

문자열은 알파벳 소문자, 공백, 숫자로만 구성되어 있다고 가정한다.

변환 예
- abc -> abc
- note -> {note}
- 1 note -> 1 {note}
- 1 note 2 -> 1 {note} 2
- keynote -> keynote
- ke1note -> ke1note
- yes note1 -> yes note1
- yes notea -> yes notea
- no a note -> no a {note}

- no a note note -> no a {note} {note}
- no a note note anote -> no a {note} {note} anote
