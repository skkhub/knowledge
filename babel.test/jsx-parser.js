module.exports = funtion() {
    return {
        manipulateOptions: function manipulateOptions(opts, parserOpts) {
            parserOpts.plugins.push('jsx');
        }
    };
};
