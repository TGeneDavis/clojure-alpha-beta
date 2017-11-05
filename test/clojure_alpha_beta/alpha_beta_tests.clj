(ns clojure-alpha-beta.alpha-beta-tests
  (:use clojure.test)
  (:require [clojure-alpha-beta.alpha-beta :as ab]))

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
  



;;;;;;;;;;;;;;;;;;;;;;;;
;;lambdas for the init

(defn my-finder
  [x] 
  (if (seq? x)
    x
    nil))

(defn my-scorer
  [x]
  (first x))

(defn my-terminal?
  [x] 
  (number?
    (first x)))




;;;;;;;;;;;;;;;;;;;;;;;;
;;now for the real tests

(deftest empty-tree
  
  (let [finder my-finder
        scorer my-scorer
        terminal? my-terminal?]
    
    (ab/init
      finder     ;;lazy-branch-finder
      scorer     ;;leaf-score-finder
      terminal?) ;;terminal
    
    (is (= 5 (ab/initial-call
               '(5)         ;;origin-node
               0         ;;depth
               true))))) ;;is-alpha))


(deftest one-ply-min-tree
  
  (let [finder my-finder
        scorer my-scorer
        terminal? my-terminal?]
            
    (ab/init
      finder     ;;lazy-branch-finder
      scorer     ;;leaf-score-finder
      terminal?) ;;terminal
  
    
    (is (= 1 (ab/initial-call
               '((5) (7) (1))         ;;origin-node
               1         ;;depth
               false))))) ;;is-alpha))


(deftest one-ply-max-tree
  
  (let [finder my-finder
        scorer my-scorer
        terminal? my-terminal?]
            
    (ab/init
      finder     ;;lazy-branch-finder
      scorer     ;;leaf-score-finder
      terminal?) ;;terminal
  
    
    (is (= 7 (ab/initial-call
               '((5) (7) (1))         ;;origin-node
               1         ;;depth
               true))))) ;;is-alpha))
          

(deftest two-max-ply-tree
  
  (let [finder my-finder
        scorer my-scorer
        terminal? my-terminal?]
            
    (ab/init
      finder     ;;lazy-branch-finder
      scorer     ;;leaf-score-finder
      terminal?) ;;terminal
  
    
    (is (= 3 (ab/initial-call
               '(((6) (3) (16)) ((-17) (10) (-5)) ((-14) (-10) (-18)))         ;;origin-node
               2         ;;depth
               true))))) ;;is-alpha))
          

(deftest two-min-ply-tree
  
  (let [finder my-finder
        scorer my-scorer
        terminal? my-terminal?]
            
    (ab/init
      finder     ;;lazy-branch-finder
      scorer     ;;leaf-score-finder
      terminal?) ;;terminal
  
    
    (is (= -10 (ab/initial-call
                 '(((6) (3) (16)) ((-17) (10) (-5)) ((-14) (-10) (-18)))         ;;origin-node
                 2         ;;depth
                 false))))) ;;is-alpha))
          

(deftest three-max-ply-tree
  
  (let [finder my-finder
        scorer my-scorer
        terminal? my-terminal?]
            
    (ab/init
      finder     ;;lazy-branch-finder
      scorer     ;;leaf-score-finder
      terminal?) ;;terminal
  
    
    (is (= 5 (ab/initial-call
               '((((6) (-4) (-14)) 
                  ((16) (-7) (15)) 
                  ((-3) (1) (1)))
                    
                 (((8) (1) (6))
                  ((13) (-14) (-10))
                  ((-8) (-17) (-17)))
                   
                 (((5) (5) (17))
                  ((6) (-14) (16))
                  ((-2) (-1) (5))))  ;; origin node 
               3                     ;;depth
               true)))))             ;;is-alpha
          
          

(deftest three-min-ply-tree
  
  (let [finder my-finder
        scorer my-scorer
        terminal? my-terminal?]
            
    (ab/init
      finder     ;;lazy-branch-finder
      scorer     ;;leaf-score-finder
      terminal?) ;;terminal
  
    
    (is (= -3 (ab/initial-call
                '((((6) (-4) (-14))
                   ((16) (-7) (15)) 
                   ((-3) (1) (1)))
                    
                  (((8) (1) (6))
                   ((13) (-14) (-10))
                   ((-8) (-17) (-17)))
                    
                  (((5) (5) (17))
                   ((6) (-14) (16))
                   ((-2) (-1) (5))))  ;; origin node 
               3                      ;;depth
               false)))))             ;;is-alpha








