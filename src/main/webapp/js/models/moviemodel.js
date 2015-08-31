window.Movie = Backbone.Model.extend({
    urlRoot: "rest/movies",
    defaults: {
        id : null,
        title : "",
        year : "",
        description : "",
        genres : "",
        picture : "generic.jpg"
    }
});

window.MovieCollection = Backbone.Collection.extend({
    initialize: function(models, options) {
        this.genre = options.genre;
    },
    model: Movie,
    /* Set url function on a collection to reference
    *  its location on to the server.
    * */
    url: function() {
        if(this.genre == ''){
            return 'rest/movies';
        }else{
            return 'rest/movies/search/genre/'+this.genre;
        }
    }
});