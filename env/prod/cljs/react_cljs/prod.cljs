(ns react-cljs.prod
  (:require
    [react-cljs.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
