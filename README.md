# clojure-alpha-beta

Copyright (c) 2017, T Gene Davis
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies,
either expressed or implied, of T Gene Davis.

----

A Clojure library for alpha-beta pruning. This library should transfer over to
ClojureScript smoothly. The alpha_beta.cljc should work in both
Clojure and ClojureScript.

Also, I've added some tests to ensure the algorithm is implmented correctly.


## Usage

The Clojure Alpha Beta Pruning library is written to handle any generic Alpha Beta 
problem. Here are the usage steps.

1. You need to create three lambdas to initialize the Clojure Alpha Beta with.
   You need a next-nodes-finder function, a nodes-scorer functions and a
   terminal-node-checker function. Write them to handle whatever your types
   of nodes look like.
2. Call the init function with the three lambdas.
3. Call the initial-call with your origin-node, depth of search, and whether
   to start with alpha or beta.

That's all. This is an extremely easy-to-use Clojure/ClojureScript 
Alpha-Beta Pruning library.
