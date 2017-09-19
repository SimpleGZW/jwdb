/**
 * Created by dushang on 2016/8/17.
 */
require.config({
    baseUrl:webRoot+"/assets/",
    paths:{
        "jQuery":"js/common/jquery-2.2.2",
        "bootstrapJs":"bootstrap/js/bootstrap.min",
        "admin":"js/admin",
        "accordion":"accordion/accordion"

    },

    shim:{
        "jQuery": { exports: "$" },
        "bootstrapJs": { deps: ["jQuery"] },
        "accordion":{deps:["jQuery"]}
    }
});

require(
    [
        "jQuery",
        "accordion",
        "bootstrapJs"
    ]
);