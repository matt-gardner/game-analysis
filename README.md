# Game Analysis

I like game theory, and building AIs to play games.  This framework was driven by an attempt to
quantify how much strategy there is in a game.  You might say there are a few dimensions along
which you could characterize a game: strategy, luck, and skill.  Strategy is basically, how much do
you have to search in order to find the optimal move at any given point.  Skill is, given that you
want to make a particular move, how hard is it to actually make that move (so, real-time games
where timing matters, anything involving hand-eye coordination, that kind of thing).  And luck is
how much does the game state change independent of the players' actions.  For example, both chess
and tic-tac-toe are completely strategic (i.e., there is no luck or skill at all in either game),
but they require very different levels of strategy.  How do you quantify that difference?

I built this over a break in school, and got to the point where I could implement a few simple
games in it, but I didn't get to actually writing any analysis code.  I also wanted to be able to
use this to actually play games, and write AIs to play the games, so I got side tracked from the
original analysis goal into writing an android framework to play the games.
