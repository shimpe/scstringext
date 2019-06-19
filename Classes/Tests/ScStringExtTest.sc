ScStringExtTest1 : UnitTest {
	test_split {
		// a few tests
        var split_scenarios = [
            [["jan, piet, joris en corneel die hebben baarden die varen mee", "en "], [ "jan, piet, joris ", "corneel die hebb", "baard", "die var", "mee"]],
            [["Twelve:12 Eighty nine:89.", "\\d+"], ["Twelve:", " Eighty nine:", "."]],
            [["apple", "banana"], ["apple"]],
            [["apple","apple"],[""]],
            [["","apple"],[]],
            [["appple", "pp"], ["a", "ple"]],
            [["pastabolognese", "banana"], ["pastabolognese"]],
            [["pastabolognese", "b.+g"], ["pasta", "nese"]],
        ];

        split_scenarios.do {
            | scenario |
            var input = scenario[0];
            var expected = scenario[1];
            var input_string = input[0];
            var input_regex = input[1];
            var result = input_string.splitRegex(input_regex);
            this.assert(result == expected);
        };
	}

    test_replace {
        var replace_scenarios = [
            [["apple", "apple", "banana"], "banana"],
            [["apples", "apple", "banana"], "bananas"],
            [["please contact support", "contact", "ignore"], "please ignore support"],
            [["12-03-2019", "\\d+", "xx"], "xx-xx-xx"],
            [["12-03-2019", "\\d", "xx"], "xxxx-xxxx-xxxxxxxx"],
            [["apple", "pear", "banana"], "apple"],
            [["appple", "pp", "cc"],"accple"]
        ];
        replace_scenarios.do {
            | scenario |
            var inputs = scenario[0];
            var output = scenario[1];
            var string = inputs[0];
            var regex = inputs[1];
            var replacement = inputs[2];
            var result = string.replaceRegex(regex, replacement);
            this.assert(result.compare(output) == 0);
        }
    }
}


ScStringExtTester {
	*new {
		^super.new.init();
	}

	init {
		ScStringExtTest1.run;
	}
}
