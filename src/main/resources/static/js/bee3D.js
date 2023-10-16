var Bee3D = function() {
    "use strict";

    function e() {}

    function t(e) {
        return Boolean(e) && e.constructor === Object
    }

    function n(e, t, n) {
        t.split(" ").forEach(function(t) {
            e.addEventListener(t, n)
        })
    }

    function i(e, t, n) {
        t.split(" ").forEach(function(t) {
            e.removeEventListener(t, n)
        })
    }

    function o(e) {
        var n;
        e = e || {};
        for (var i = 1; i < arguments.length; i++) {
            n = Object(arguments[i]);
            for (var a in n)
                e[a] = t(n[a]) ? o(e[a], n[a]) : n[a]
        }
        return e
    }

    function a() {
        if (l.XMLHttpRequest)
            return new l.XMLHttpRequest;
        try {
            return new l.ActiveXObject("MSXML2.XMLHTTP.3.0")
        } catch (e) {}
        throw new Error("no xmlhttp request able to be created")
    }

    function s(e, t, n) {
        e[t] = e[t] || n
    }

    function r(e, t) {
        this.options = o({}, u, t),
            this.init(e)
    }
    var l = window,
        c = document,
        u = {
            wrapper: c.body,
            selector: ".bee3D--slide",
            effect: "coverflow",
            focus: 0,
            listeners: {
                keys: !1,
                touches: !1,
                clicks: !1,
                scroll: !1,
                drag: !1
            },
            navigation: {
                enabled: !1,
                next: ".bee3D--nav__next",
                prev: ".bee3D--nav__prev"
            },
            ajax: {
                enabled: !1,
                path: null,
                when: 2,
                maxFetches: null,
                builder: function(e) {
                    return "<p>" + e.content + "</p>"
                }
            },
            autoplay: {
                enabled: !1,
                speed: 5e3,
                pauseHover: !1
            },
            loop: {
                enabled: !1,
                continuous: !1,
                offset: 2
            },
            sync: {
                enabled: !1,
                targets: []
            },
            parallax: {
                enabled: !1,
                className: "bee3D--parallax",
                friction: .7,
                settings: {
                    relativeInput: !0,
                    clipRelativeInput: !0,
                    calibrateX: !0,
                    calibrateY: !0,
                    scalarX: 4,
                    scalarY: 5,
                    frictionX: .1,
                    frictionY: .1
                }
            },
            shadows: {
                enabled: !1,
                template: ['<div class="bee3D--shadow-wrapper">', '<div class="bee3D--shadow">', "<span></span>", "</div>", "</div>"].join("")
            },
            onInit: e,
            onChange: e,
            onDestroy: e
        },
        f = function(e, t) {
            "string" == typeof e && (e = {
                url: e
            });
            var n = e.headers || {},
                i = e.body,
                o = e.method || (i ? "POST" : "GET"),
                r = e.withCredentials || !1,
                l = a();
            l.onreadystatechange = function() {
                    4 === l.readyState && t(l.status, l.responseText, l)
                },
                i && (s(n, "X-Requested-With", "XMLHttpRequest"),
                    s(n, "Content-Type", "application/x-www-form-urlencoded")),
                l.open(o, e.url, !0),
                l.withCredentials = r;
            for (var c in n)
                n.hasOwnProperty(c) && l.setRequestHeader(c, n[c]);
            l.send(i)
        },
        d = function() {
            var e = this,
                t = e.options.ajax,
                n = t.when,
                i = t.path,
                o = t.maxFetches,
                a = t.builder,
                s = e.el.slides,
                r = s.length,
                l = e.options.selector.substring(1),
                u = function(t) {
                    e.el.parent.appendChild(t),
                        e.el.slides.push(t)
                },
                d = function(n) {
                    var i = n.map(function(e) {
                        var t = c.createElement("section");
                        return t.className = l,
                            t.innerHTML = '<div class="bee3D--inner">' + a(e) + "</div>",
                            u(t),
                            t
                    });
                    return r = e.el.slides.length,
                        e.slideEvents(i),
                        t.maxFetches && o--,
                        e.el.touch()
                },
                h = function() {
                    f(i, function(e, t) {
                        return 200 === e ? d(JSON.parse(t).data) : void 0
                    })
                },
                p = function(e) {
                    if (r - n === e.index) {
                        if (!t.maxFetches)
                            return h();
                        if (o && o > 0)
                            return h()
                    }
                };
            this.el.on("activate", p)
        },
        h = function() {
            var e = this,
                t = function() {
                    e.timer = setTimeout(function() {
                        e.el.next()
                    }, e.options.autoplay.speed)
                },
                n = function() {
                    clearTimeout(e.timer)
                },
                i = function() {
                    n(),
                        t()
                };
            t(),
                e.el.on("resumeAutoplay", t),
                e.el.on("pauseAutoplay", n),
                e.el.on("resetAutoplay", i),
                e.el.on("activate", i),
                e.options.autoplay.pauseHover && e.el.on("activate", function(t) {
                    e.listenToHover(t.slide)
                }),
                e.el.on("destroy", function() {
                    n()
                })
        },
        p = function(e) {
            for (var t = this, o = function() {
                    var n = e.indexOf(this);
                    return t.el.slide(n)
                }, a = 0; a < e.length; a++)
                e[a].style.pointerEvents = "auto",
                e[a].style.cursor = "pointer",
                n(e[a], "click", o);
            this.el.on("activate", function(e) {
                    i(e.slide, "click", o)
                }),
                this.el.on("deactivate", function(e) {
                    n(e.slide, "click", o)
                }),
                this.el.on("destroy", function() {
                    e.forEach(function(e) {
                        e.removeAttribute("style"),
                            i(e, "click", o)
                    })
                })
        },
        v = function() {
            var e = this.el;
            e.on("prev", function(t) {
                    0 === t.index && e.slide(e.slides.length - 1)
                }),
                e.on("next", function(t) {
                    t.index === e.slides.length - 1 && e.slide(0)
                })
        },
        m = function() {
            var e, t, o = this,
                a = this.el.parent,
                s = function(n) {
                    e = n.x,
                        t = 0
                },
                r = function(n) {
                    n.preventDefault(),
                        t = n.x - e
                },
                l = function() {
                    Math.abs(t) > 50 && o.el[t > 0 ? "prev" : "next"]()
                };
            classie.add(a, "draggable"),
                n(a, "mousedown", s),
                n(a, "mousemove", r),
                n(a, "mouseup", l),
                this.el.on("destroy", function() {
                    classie.remove(a, "draggable"),
                        i(a, "mousedown", s),
                        i(a, "mousemove", r),
                        i(a, "mouseup", l)
                })
        },
        b = function() {
            var e = this,
                t = this.el.parent,
                o = function(t) {
                    var n = t.wheelDelta || -t.detail;
                    return n < 0 ? e.el.next() : e.el.prev()
                };
            n(t, "mousewheel DOMMouseScroll", o),
                this.el.on("destroy", function() {
                    i(t, "mousewheel DOMMouseScroll", o)
                })
        },
        y = function() {
            var e = this,
                t = e.options,
                i = e.el.parent,
                o = function(i, o) {
                    n(i, "click", function(t) {
                            return t.preventDefault(),
                                o ? e.el.next() : e.el.prev()
                        }),
                        t.autoplay.enabled && t.autoplay.pauseHover && e.listenToHover(i)
                },
                a = i.querySelector(t.navigation.prev),
                s = i.querySelector(t.navigation.next);
            s && o(s, !0),
                a && o(a, !1)
        },
        x = function(e) {
            if (l.Parallax) {
                for (var t = this, n = t.options, i = n.shadows.enabled, o = n.parallax.className, a = n.parallax.friction, s = n.parallax.settings, r = function(e) {
                        classie.add(e, o),
                            e.setAttribute("data-depth", a)
                    }, c = 0; c < e.length; c++) {
                    var u = e[c];
                    r(u.firstElementChild),
                        i && r(u.lastChild)
                }
                s.className = o,
                    t._parallax = new Parallax(t.el.parent, n.parallax.settings),
                    t.el.parent.style.transformStyle = "initial",
                    t.el.on("destroy", function() {
                        t.el.parent.removeAttribute("style"),
                            t._parallax.disable();
                        var e = t._parallax.layers;
                        t._parallax = t._parallax.layers = t._parallax.element = void 0;
                        for (var n = e.length - 1; n >= 0; n--) {
                            var i = e[n];
                            classie.remove(i, o),
                                i.removeAttribute("data-depth"),
                                i.removeAttribute("style")
                        }
                    })
            }
        },
        g = function(e) {
            var t = this.options.shadows.template;
            e.forEach(function(e) {
                    e.innerHTML += t
                }),
                this.el.on("destroy", function() {
                    e.forEach(function(e) {
                        e.removeChild(e.lastChild)
                    })
                })
        },
        w = function() {
            var e = this.options.sync.targets,
                t = function(t) {
                    for (var n = t.index, i = 0; i < e.length; i++)
                        l[e[i]].el.slide(n)
                };
            this.el.on("activate", t)
        },
        D = function(e) {
            return function(t) {
                var o = "vertical" !== e,
                    a = function(e) {
                        (34 === e.which || 32 === e.which || o && 39 === e.which || !o && 40 === e.which) && t.next(),
                            (33 === e.which || o && 37 === e.which || !o && 38 === e.which) && t.prev()
                    };
                n(c, "keydown", a),
                    t.on("destroy", function() {
                        i(c, "keydown", a)
                    })
            }
        },
        _ = function(e) {
            var t = "bee3D--",
                n = e.loop.continuous,
                i = e.loop.offset;
            return function(o) {
                var a = o.slides.length,
                    s = function(e, n) {
                        classie.add(e, t + n)
                    },
                    r = function(e, n) {
                        e.className = e.className.replace(new RegExp(t + n + "(\\s|$)", "g"), " ").trim()
                    },
                    l = function(e, t) {
                        var l = o.slide(),
                            c = t - l,
                            u = c > 0 ? "after" : "before";
                        if (n) {
                            var f = a - i - 1;
                            c >= f && (u = "before",
                                    c = a - c),
                                c <= -f && (u = "after",
                                    c = a + c)
                        }
                        ["before(-\\d+)?", "after(-\\d+)?", "slide__active", "slide__inactive"].map(r.bind(null, e)),
                            t !== l && ["slide__inactive", u, u + "-" + Math.abs(c)].map(s.bind(null, e))
                    };
                s(o.parent, "parent"),
                    ".bee3D--slide" !== !e.slideSelector && o.slides.forEach(function(e) {
                        s(e, "slide")
                    }),
                    o.on("activate", function(e) {
                        o.slides.map(l),
                            s(e.slide, "slide__active"),
                            r(e.slide, "slide__inactive")
                    })
            }
        },
        E = function() {
            return function(e) {
                function t(e) {
                    s = e.touches[0].pageX,
                        r = e.touches[0].pageY,
                        l = 0
                }

                function o(e) {
                    var t = e.touches[0];
                    if (Math.abs(s - t.pageX) > Math.abs(r - t.pageY))
                        return l = t.pageX - s, !1
                }

                function a() {
                    Math.abs(l) > 50 && e[l > 0 ? "prev" : "next"]()
                }
                var s, r, l, c = e.parent;
                n(c, "touchstart", t),
                    n(c, "touchmove", o),
                    n(c, "touchend", a),
                    e.on("destroy", function() {
                        i(c, "touchstart", t),
                            i(c, "touchmove", o),
                            i(c, "touchend", a)
                    })
            }
        },
        M = function(e) {
            return function(t) {
                t.on("activate", function(n) {
                    if (t.initialized)
                        return e(n)
                })
            }
        },
        k = function(e) {
            var t = [].slice.call(e),
                n = t[-1],
                i = {},
                o = function() {
                    return t.indexOf(n)
                },
                a = function(e, n) {
                    return n = n || {},
                        n.index = t.indexOf(e),
                        n.slide = e,
                        n
                },
                s = function(e, t) {
                    return (i[e] || []).reduce(function(e, n) {
                        return e && n(t) !== !1
                    }, !0)
                },
                r = function(e) {
                    return s("activate", a(n, e))
                },
                l = function(e, i) {
                    t[e] && (s("deactivate", a(n, i)),
                        n = t[e],
                        r(i))
                },
                c = function(e, n) {
                    var i = o();
                    if (i !== e)
                        return arguments.length ? (s("slide", a(t[e], n)),
                            void l(e, n)) : i
                },
                u = function(e, i) {
                    var o = t.indexOf(n) + e;
                    s(e > 0 ? "next" : "prev", a(n, i)),
                        l(o, i)
                },
                f = function(e, t) {
                    return i[e] = (i[e] || []).concat(t),
                        function() {
                            i[e] = i[e].filter(function(e) {
                                return e !== t
                            })
                        }
                };
            return {
                on: f,
                fire: s,
                touch: r,
                slide: c,
                next: u.bind(null, 1),
                prev: u.bind(null, -1),
                slides: t
            }
        },
        H = function(e) {
            function t() {
                a.el.fire("pauseAutoplay")
            }

            function o() {
                a.el.fire("resetAutoplay")
            }
            var a = this;
            n(e, "mouseover", t),
                n(e, "mouseout", o),
                a.el.on("destroy", function() {
                    i(e, "mouseover", o),
                        i(e, "mouseout", t)
                })
        },
        S = function() {
            var e = this.el.parent,
                t = this.el.slides,
                n = new RegExp("bee3D-(.*)", "g");
            e.className = e.className.replace(n, "");
            for (var i = ".bee3D--slide" === this.options.selector, o = 0; o < t.length; o++)
                t[o].className = i ? "bee3D--slide" : t[o].className.replace(n, "");
            this.el.fire("destroy");
            var a = this.options.onDestroy;
            this.options = u,
                this.plugins(),
                a()
        };
    return r.prototype = {
            init: function(e) {
                var t = this.options,
                    n = e.querySelectorAll(t.selector);
                this.el = k(n),
                    this.el.parent = e,
                    this.plugins(),
                    this.el.slide(this.options.focus),
                    classie.add(this.el.parent, "bee3D--effect__" + this.options.effect),
                    this.events(),
                    this.slideEvents(),
                    this.options.onInit(),
                    this.el.initialized = !0
            },
            plugins: function() {
                var e = this,
                    t = e.options,
                    n = [_(t), M(t.onChange)];
                t.listeners.keys && n.push(D()),
                    t.listeners.touches && n.push(E()),
                    (n || []).forEach(function(t) {
                        t(e.el)
                    })
            },
            events: function() {
                var e = this.options;
                e.sync.enabled && this.sync(),
                    e.ajax.enabled && this.ajax(),
                    e.loop.enabled && this.loop(),
                    e.autoplay.enabled && this.autoplay(),
                    e.navigation.enabled && this.navigation(),
                    e.listeners.scroll && this.mouseScroll(),
                    e.listeners.drag && this.mouseDrag()
            },
            slideEvents: function(e) {
                var t = this.options;
                e || (e = this.el.slides),
                    t.shadows.enabled && this.shadows(e),
                    t.parallax.enabled && this.parallax(e),
                    t.listeners.clicks && this.clickInactives(e)
            },
            sync: w,
            ajax: d,
            loop: v,
            shadows: g,
            autoplay: h,
            navigation: y,
            parallax: x,
            clickInactives: p,
            mouseScroll: b,
            mouseDrag: m,
            destroy: S,
            listenToHover: H
        },
        r
}();