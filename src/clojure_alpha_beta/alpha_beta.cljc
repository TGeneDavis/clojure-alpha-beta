(ns clojure-alpha-beta.alpha-beta)

;; Look for more information at https://makeonlinegames.com/




;; Copyright (c) 2017, T Gene Davis
;; All rights reserved.

;; Redistribution and use in source and binary forms, with or without
;; modification, are permitted provided that the following conditions are met:

;; 1. Redistributions of source code must retain the above copyright notice, this
;;    list of conditions and the following disclaimer.
;; 2. Redistributions in binary form must reproduce the above copyright notice,
;;    this list of conditions and the following disclaimer in the documentation
;;    and/or other materials provided with the distribution.

;; THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
;; ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
;; WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
;; DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
;; ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
;; (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
;; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
;; ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
;; (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
;; SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

;; The views and conclusions contained in the software and documentation are those
;; of the authors and should not be interpreted as representing official policies,
;; either expressed or implied, of T Gene Davis.
  






;;----------------------------------------------------------------------------------------  
;; **TODO**  **TODO**  **TODO**  **TODO**  **TODO**  **TODO** 

;; return the path to the best solution

;; deep cutoff (not sure if it is happening or not ... brain got tired)

;; All kinds of edge cases can only be tested at 4-plies and deeper ... research and test

;; Test uneven tree development, using expansion to explore interesting nodes

;; Add node sorting based off iterative deepening depth first search

;;
;;----------------------------------------------------------------------------------------  




(def neg-infinity (with-meta '(-2000000000) {:source "root"}))
(def pos-infinity (with-meta '(2000000000) {:source "root"}))

(defn min-score
  "Takes two seqs with first element being a long value."
  [x y]
  (if (= (first x) (min (first x) (first y)))
    x
    y))

(defn max-score
  "Takes two seqs with first element being a long value."
  [x y]
  (if (= (first x) (max (first x) (first y)))
    x
    y))

(defn >-score
  "Takes two seqs with first element being a long value."
  [x y]
  (> (first x) (first y)))


(declare abnode);;avoid ciclic reference issues
(declare lazy-branch-finder);;must be lazy!
(declare leaf-score-finder);;game-specific scoring algorithm
(declare terminal-node?);;if the king is gone, it's a terminal node


(defn maximizing-player
   [branches           ;;lazy seq of branches
    current-node       ;;current board/node
    search-depth       ;;remaining depth to search. 1 means this node is a leaf
    best-alpha         ;;current best alpha maximum found
    best-beta]          ;;current best beta minimum found
   
  (loop [v1 neg-infinity
         node (first branches)
         next-branches (rest branches)
         current-alpha best-alpha]
    (let [v2 (max-score 
               v1
               (abnode node (dec search-depth) current-alpha best-beta false))]

      (let [new-alpha (max-score best-alpha v2)]
        (if (and (>-score best-beta new-alpha) (first next-branches))
          (recur 
            v2 
            (first next-branches)
            (rest next-branches)
            new-alpha)
          v2)))))
          



(defn minimizing-player
   [branches           ;;lazy seq of branches
    current-node       ;;current board/node
    search-depth       ;;remaining depth to search. 1 means this node is a leaf
    best-alpha         ;;current best alpha maximum found
    best-beta]          ;;current best beta minimum found
   
  (loop [v1 pos-infinity
         node (first branches)
         next-branches (rest branches)
         current-beta best-beta]

    (let [v2 (min-score 
               v1
               (abnode node (dec search-depth) best-alpha current-beta true))]

      (let [new-beta (min-score best-beta v2)]
        (if (and (>-score new-beta best-alpha) (first next-branches))
          (recur 
            v2 
            (first next-branches)
            (rest next-branches)
            new-beta)
          v2)))))



(defn abnode
  "Alpha-beta node representation ... should be called by initial-call"
  [current-node       ;;current board/node
   search-depth       ;;remaining depth to search. 1 means this node is a leaf
   best-alpha         ;;current best alpha maximum found
   best-beta          ;;current best beta minimum found
   is-alpha]          ;;true if the is a max node. false if this is a min node
  
  (let [branches (lazy-branch-finder current-node)]
    (if (or (= 0 search-depth) (terminal-node? current-node))
      (leaf-score-finder current-node) ;;returning value of current node
      (if is-alpha
        
        (maximizing-player 
          branches 
          current-node 
          search-depth 
          best-alpha 
          best-beta) 
        
        (minimizing-player 
          branches 
          current-node 
          search-depth 
          best-alpha 
          best-beta))))) 





(defn init
  "Call this *BEFORE* you use the initial-call function."
  [branch-finder   ;;method for finding moves with new board nodes
   score-finder    ;;method for finding the score of a given node
   terminal?] ;;method for finding the score of a given node
  
  ;;setting the lazy node generator and leaf node score finder
  (def lazy-branch-finder branch-finder)
  (def leaf-score-finder score-finder)
  (def terminal-node? terminal?))





(defn initial-call
  "Call to start the search."
  [origin-node
   depth
   is-alpha]
  
  (abnode origin-node depth neg-infinity pos-infinity is-alpha))

  










