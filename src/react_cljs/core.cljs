(ns react-cljs.core
  "main frontend logic"
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [react-cljs.ui :as ui]))

;; -----------------------
;; Styles
(defonce drawer-width 240)

(def styles
  (fn [th]
    (let [theme (js->clj th :keywordize-keys true)]
      (clj->js
       {:root {:display "flex"}
        :toolbar {:paddingRight 24}
        :toolbarIcon
        {:display "flex"
         :alignItems "center"
         :justifyContent "flex-end"
         :padding "0 8px"}
        :appbar
        {:zIndex (+ 1 (get-in theme [:zIndex :drawer]))
         :transition
         ((get-in theme [:transitions :create])
          #js ["width" "margin"]
          #js {:easing (get-in theme [:transitions :easing :sharp])
               :duration (get-in theme [:transitions :duration :leavingScreen])})}
        :appbarShift
        {:marginLeft drawer-width
         :width (str "calc (100% - " drawer-width "px")
         :transition
         ((get-in theme [:transitions :create])
          #js ["width" "margin"]
          #js {:easing (get-in theme [:transitions :easing :sharp])
               :duration (get-in theme [:transitions :duration :enteringScreen])})}
        :menuButton {:marginRight 36}
        :menuButtonHidden {:menuButtonHidden {:display "none"}}
        :title {:flexGrow 1}
        :drawerPaper
        {:position "relative"
         :whiteSpace "nowrap"
         :width drawer-width
         :transition
         ((get-in theme [:transitions :create])
          "width"
          #js {:easing (get-in theme [:transitions :easing :sharp])
               :duration (get-in theme [:transitions :duration :enteringScreen])})}
        :drawerPaperClose
        {:overflowX "hidden"
         :transition
         ((get-in theme [:transitions :create])
          "width"
          #js {:easing (get-in theme [:transitions :easing :sharp])
               :duration (get-in theme [:transitions :duration :leavingScreen])})
         :width ((get-in theme [:spacing]) 7)
         ((get-in theme [:breakpoints :up]) "sm")
         {:width ((get-in theme [:spacing]) 9)}}
        :appbarSpacer (get-in theme [:mixins :toolbar])
        :content {:fexGrow 1
                  :height "100vh"
                  :overflow "auto"}
        :container {:paddingTop ((get-in theme [:spacing]) 4)
                    :paddingBottom ((get-in theme [:spacing]) 4)}
        :paper {:padding ((get-in theme [:spacing]) 2)
                :display "flex"
                :overflow "auto"
                :flexDirection "column"}
        :fixedHeight {:height 240}}))))

;; -------------------------
;; Views
(def main-list-items 
  [:div
   [:> ui/list-item {:button true}
    [:> ui/list-item-icon
     [:> ui/dashboard-icon]
     [:> ui/list-item-text {:primary "Dashboard"}]]]
   [:> ui/list-item {:button true}
    [:> ui/list-item-icon
     [:> ui/dashboard-icon]
     [:> ui/list-item-text {:primary "Orders"}]]]
   [:> ui/list-item {:button true}
    [:> ui/list-item-icon
     [:> ui/dashboard-icon]
     [:> ui/list-item-text {:primary "Customers"}]]]
   [:> ui/list-item {:button true}
    [:> ui/list-item-icon
     [:> ui/dashboard-icon]
     [:> ui/list-item-text {:primary "Reports"}]]]
   [:> ui/list-item {:button true}
    [:> ui/list-item-icon
     [:> ui/dashboard-icon]
     [:> ui/list-item-text {:primary "Integrations"}]]]])

(defn home-page []
  (let [open (r/atom false)]
    (fn [{:keys [classes]}]
      (.log js/console @open)
      [:div {:class (. classes -root)}
       [:> ui/css-baseline]
       [:> ui/appbar {:position "absolute"
                      :class [(. classes -appbar) (if @open (. classes -appbarShift) "")]}
        [:> ui/toolbar {:class (. classes -toolbar)}
         [:> ui/icon-button {:edge "start" 
                             :color "inherit" 
                             :aria-label "open drawer"
                             :onClick #(reset! open true)
                             :class [(. classes -menuButton)
                                     (if @open (. classes -menuButtonHidden) "")]}
          [:> ui/menu-icon]]
         [:> ui/typography {:component "h1" 
                            :variant "h6" 
                            :color "inherit"
                            :noWrap true 
                            :class (. classes -title)}
          "Dashboard"]
         [:> ui/icon-button {:color "inherit"}
          [:> ui/badge {
                        :badgeContent 4 
                        :color "secondary"}
           [:> ui/notifications-icon]]]]]
       [:> ui/drawer {:open @open 
                      :variant "permanent"
                      :class [(. classes -drawerPaper)
                              (if-not @open (. classes -drawerPaperClose) "")]}
        [:div {:class (. classes -toolbarIcon)}
         [:> ui/icon-button {:onClick #(reset! open false)}
          [:> ui/chevron-left-icon]]]
        [:> ui/divider]
        [:> ui/list
         main-list-items
         ]]
       [:main {:class (. classes  -content)}
        [:div {:class (. classes -appbarSpacer)}]
        [:> ui/container {:maxWidth "lg" 
                          :class (. classes -container)}
         [:> ui/grid {:container true 
                      :spacing 3}]]]])))

;; -------------------------
;; Initialize app

(def styled-page
  (-> home-page
      (r/reactify-component)
      ((ui/with-styles styles) )
      (r/adapt-react-class)))

(defn mount-root []
  (d/render [styled-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
