
### Development mode
To start the Figwheel compiler, navigate to the project folder and run the following command in the terminal:

```
lein figwheel
```

Figwheel will automatically push cljs changes to the browser.
Once Figwheel starts up, you should be able to open the `public/index.html` page in the browser.

### REPL

The project is setup to start nREPL on port `7002` once Figwheel starts.
Once you connect to the nREPL, run `(cljs)` to switch to the ClojureScript REPL.

### Building for production

```
lein clean
lein package
```

###

#### BUG 1

`js->clj`对这段文字会解析错误, 但在REPL里不会

```
(def t #js {:menu-button-hidden "react_cljs.core.home_page-menu-button-hidden-106"
            :drawer-paper-close "react_cljs.core.home_page-drawer-paper-close-107"
            :fixed-height "react_cljs.core.home_page-fixed-height-108"
            :content "react_cljs.core.home_page-content-109"
            :toobarIcon "react_cljs.core.home_page-toobarIcon-110"
            :paper "react_cljs.core.home_page-paper-111"
            :title "react_cljs.core.home_page-title-112"
            :appbar "react_cljs.core.home_page-appbar-113"
            :root "react_cljs.core.home_page-root-114"
            :appbar-spacer "react_cljs.core.home_page-appbar-spacer-115"
            :container "react_cljs.core.home_page-container-116"
            :toolbar "react_cljs.core.home_page-toolbar-117"
            :appbar-shift "react_cljs.core.home_page-appbar-shift-118"
            :menu-button "react_cljs.core.home_page-menu-button-119"
            :drawer-paper "react_cljs.core.home_page-drawer-paper-120"})
```

#### BUG 2

设置的`class`不起作用