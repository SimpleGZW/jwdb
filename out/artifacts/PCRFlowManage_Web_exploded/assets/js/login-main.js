/**
 * Created by Administrator on 2016/8/22 0022.
 */
require.config({
    baseUrl: webRoot+"/assets/",
    paths:{
        "jQuery":"js/common/jquery-2.2.2",
        "bootstrapJs":"bootstrap/js/bootstrap.min",

        "login":"js/login"
    },

    shim:{
        "jQuery": { exports: "$" },
        "bootstrapJs": { deps: ["jQuery"] }
    }
});

require(
    [
        "jQuery",
        "login"
    ]
);