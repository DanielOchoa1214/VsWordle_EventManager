apiclient = (function () {

    let _handleError = function () {
        $("#successful-search").addClass("remove");
        $("#search-error").removeClass("remove");
    }

	return {
		getBlueprintsByAuthor:function(authname, callback){
            $.get(`http://localhost:8080/blueprints/${authname}`, (data) => {
                callback(data);
            }).fail(_handleError);
		},

		getBlueprintsByNameAndAuthor:function(authname, bpname,callback){
            $.get(`http://localhost:8080/blueprints/${authname}/${bpname}`, (data) => {
                callback(data);
            }).fail(_handleError);
		},

        putBlueprint: function (blueprint) {
            return $.ajax({
                url: `http://localhost:8080/blueprints/${blueprint.author}/${blueprint.name}`,
                type: 'PUT',
                data: JSON.stringify(blueprint),
                error: _handleError,
                contentType: "application/json",
                success: function (result) {
                    alert("Se guardado o actualizado el blueprint");
                }
            });
        },

        postBlueprint: function (blueprint) {
            return $.ajax({
                url: `http://localhost:8080/blueprints`,
                type: 'POST',
                data: JSON.stringify(blueprint),
                error: _handleError,
                contentType: "application/json",
                success: function (result) {
                    alert("Se creo el blueprint exitosamente");
                }
            });
        },

        deleteBlueprint: function (authname, bpName) {
            return $.ajax({
                url: `http://localhost:8080/blueprints/${authname}/${bpName}`,
                type: 'DELETE',
                error: _handleError,
                contentType: "application/json",
                success: function (result) {
                    alert("Se ha eliminado el blueprint exitosamente");
                }
            });
        }
	}	

})();