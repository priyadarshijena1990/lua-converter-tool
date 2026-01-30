function foo(a) {
    if (a > 0) {
        for (var i = 0; i < a; i++) {
            // do something
        }
    } else {
        while (a < 10) a++;
    }
}
foo(5);
