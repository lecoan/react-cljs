(ns react-cljs.ui
  "wrapper for MaterialUI"
  (:require
   [cljsjs.material-ui]
   [material-ui-icons]))

;; -----------------------
;; Compnents
(def ui js/MaterialUI)

(def button (. ui -Button))
(def box (. ui -Box))
(def container (. ui -Container))
(def css-baseline (. ui -CssBaseline))
(def typography (. ui -Typography))
(def appbar (. ui -AppBar))
(def toolbar (. ui -Toolbar))
(def icon-button (. ui -IconButton))
(def badge (. ui -Badge))
(def drawer (. ui -Drawer))
(def divider (. ui -Divider))
(def list (. ui -List))
(def grid (. ui -Grid))
(def list-item (. ui -ListItem))
(def list-item-text (. ui -ListItemText))
(def list-item-icon (. ui -ListItemIcon))

;; -----------------------
;; Style
(def st js/MaterialUIStyles)

(def make-style (. st -makeStyles))
(def with-styles (. st -withStyles))
(def create-theme (. ui -createMuiTheme))

;; -----------------------
;; Icons
(def ic js/MaterialUIIcons)

(def menu-icon (. ic -Menu))
(def notifications-icon (. ic -Notifications))
(def chevron-left-icon (. ic -ChevronLeft))
(def dashboard-icon (. ic -Dashboard))