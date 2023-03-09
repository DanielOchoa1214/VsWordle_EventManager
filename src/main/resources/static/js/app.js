//@author hcadavid

let app = (function (api) {
    let _author = "";
    let _blueprints = [];
    let publicFunctions = {};

    let _bpsToObject = (data) => {
        objData = data.map((x) => (
            {
                name: x.name,
                pointsNum : x.points.length
            }
        ));
        $("#blueprints tbody").text("");
        objData.map((x) => {
            $(document).ready(() => {
                let markup = `<tr><td>${x.name}</td><td>${x.pointsNum}</td></tr>`;
                $("#blueprints tbody").append(markup);
            });
        });
        let totalPoints = objData.reduce((total, i) => total + i.pointsNum, 0);
        $(document).ready(() => {
            $("#total-points").html(totalPoints);
        });
        _blueprints = objData;
    }

    publicFunctions.setName = function (newName) {
        _author = newName;
    }

    publicFunctions.updateBlueprints = function (authorName) {
        api.getBlueprintsByAuthor(authorName, _bpsToObject);
    }

    return publicFunctions;

})(apimock);


/*
Example of use:
var fun=function(list){
	console.info(list);
}
app.setName("svbujsfvbnsj")

apimock.getBlueprintsByAuthor("johnconnor",fun);
apimock.getBlueprintsByNameAndAuthor("johnconnor","house",fun);*/