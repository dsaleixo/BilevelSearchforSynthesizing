# Show Me the Way! Bilevel Search for Synthesizing Programmatic Strategies

by
David S. Aleixo,
Levi H. S. Levis

This paper was submitted and accepted for publication in *AAAI-2023*.

## Abstract
The synthesis of programmatic strategies requires one to
search in large non-differentiable spaces of computer pro-
grams. Current search algorithms use self-play approaches to
guide this search. The issue with these approaches is that the
guiding function often provides a weak search signal. This is
because self-play functions only measure how well a program
performs against other programs. Thus, while small changes
to a losing program might not transform it into a winning
one, such changes might represent steps in the direction of a
winning program. In this paper we introduce a bilevel search
algorithm that searches concurrently in the space of programs
and in a space of state features. Each iteration of the search
in the space of features defines a set of target features that
the search in the program space attempts to achieve (i.e., fea-
tures one observes while following the strategy encoded in
a program). We hypothesize the combination of a self-play
function and a feature-based one provides a stronger search
signal for synthesis. While both functions are used to guide
the search in the program space, the self-play function is
used to guide the search in the feature space, to allow for
the selection of target features that are more likely to lead
to winning programs. We evaluated our bilevel algorithm in
MicroRTS, a real-time strategy game. Our results show that
the bilevel search synthesizes stronger strategies than meth-
ods that search only in the program space. Also, the strategies
our method synthesizes obtained the highest winning rate in a
simulated tournament with several baseline agents, including
the best agents from the two latest MicroRTS competitions.

## Code Structure
including the https://github.com/dsaleixo/MicroRTS2 project, the main file is in \LocalSearch\Tests where you can find the algorithms shown in the article.
a DSL se encontra em LocalSearch\LS_CFG e a classe Controle tranforma uma AST em string e vice-versa

## License

Distributed under the MIT License.

```
Copyright (c) 2022 Leandro C. Medeiros, David S. Aleixo, Levi H. S. Levis

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
