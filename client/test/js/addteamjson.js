function getAddTeam(teamName, img) {
    return {
        "name": "addteam",
        reqbody: {
            "gametype": gametype,
            "name": teamName,
            "img": img,
            "session": storageGet(SESSION_STR)
        }
    }
}

function getAddTeamGroup(teamName) {
    return {
        "name": "addteamgroup",
        reqbody: {
            "gametype": gametype,
            "name": teamName,
            "session": storageGet(SESSION_STR)
        }
    }
}

function getDeleteTeam(id) {
    return {
        "name": "deleteteam",
        reqbody: {

            "id": id,
            "session": storageGet(SESSION_STR)
        }
    }
}

function getDeleteTeamGroup(id) {
    return {
        "name": "deleteteamgroup",
        reqbody: {

            "id": id,
            "session": storageGet(SESSION_STR)
        }
    }
}
// "type":1,  //1、王者荣耀 2.绝地求生
//     "name":"LGD",//战队名字
//     "pageIndex" : 0 , //当前页码
//     "pageSize" : 2    //每页条数
function getTeamList(teamName, pageIndex) {
    return {
        "name": "teamlist",
        "reqbody": {
            "type": gametype,
            //1、王者荣耀 2.绝地求生
            "name": teamName,
            //战队名字
            "pageIndex": pageIndex,
            //当前页码
            "pageSize": 50,
          //每页条数
          "session": storageGet(SESSION_STR)
        }
    }
}
