+ String {
    splitRegex {
        | regex |
        var allregexp = this.findAllRegexp(regex);
        var startpos = 0;
        var result = [];
        allregexp.do {
            | pos |
            var match = this.findRegexpAt(regex, pos);
            var matchlength = match[1];
            result = result.add(this.copyRange(startpos, pos-1));
            startpos = pos + matchlength;
        };
        if (startpos < this.size) {
            result = result.add(this.copyRange(startpos, this.size-1));
        };
        ^result;
    }

    replaceRegex {
        | regex, replacement |
        var allregexp = this.findAllRegexp(regex);
        var startpos = 0;
        var result = "";
        allregexp.do {
            | pos |
            var match = this.findRegexpAt(regex, pos);
            var matchlength = match[1];
            result = result ++ (this.copyRange(startpos, pos-1) ++ replacement);
            startpos = pos + matchlength;
        };
        if (startpos < this.size) {
            result = result ++ this.copyRange(startpos, this.size-1);
        };
        ^result;
    }
}
