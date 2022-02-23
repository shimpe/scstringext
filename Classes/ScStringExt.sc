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

	betweenRegexPos {
		| startregex, endregex, startregex_idx=0, endregex_idx=0 |
		var startpos, endpos, spos, epos;
		startpos = this.findRegexp(startregex);
		spos = 0;
		if (startpos.size <= startregex_idx) {
			^"";
		} {
			spos = startpos[startregex_idx][0] + startpos[startregex_idx][1].size;
		};
		endpos = this.findRegexp(endregex);
		if (endpos.size <= endregex_idx) {
			^"";
		} {
			epos = endpos[endregex_idx][0];
		}
		^[spos, epos];
	}

	betweenRegex {
		| startregex, endregex, startregex_idx=0, endregex_idx=0 |
		var pos = this.betweenRegexPos(startregex, endregex, startregex_idx, endregex_idx);
		^this.copyRange(pos[0], pos[1]-1);
	}

}
