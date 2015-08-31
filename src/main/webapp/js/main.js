Backbone.View.prototype.close = function(){
    console.log('Closing view: '+this);
    if(this.beforeClose){
        this.beforeClose();
    }
    this.remove();
    this.unbind();
};

var Approuter = Backbone.Router.extend({
    initialize: function(){
        $('#header').html(new HeaderView().render().el);
    },
    /*  Mapping to particular functions */
    routes: {
        "" : "list",
        "movies" : "list",
        "movies/new" : "newMovie",
        "movies/searchGenre" : "searchByGenre",
        "movies/:id" : "movieDetails"
    },
    /* Mapped functions */
    searchByGenre: function(){
        if(this.currentView){
            this.currentView.close();
        }
        this.before();
    },

    list: function(){
        $('#search_genre_input').val('');
        app.movieList = null;
        app.searchList = null;
        this.before();
    },

    movieDetails: function(id){
        this.before(function (){
            var movie = app.movieList != null ? app.movieList.get(id) : app.searchList.get(id)
            app.showView('#content',new MovieView({model: movie}));
        });
    },

    newMovie: function(){
        this.before(function(){
            app.showView('#content', new MovieView({model: new Movie()}));
        });
    },

    showView: function(selector,view){
        if(this.currentView){
            this.currentView.close();
        }
        $(selector).html(view.render().el);
        this.currentView = view;
        return view;
    },

    before: function(callback){
        var genreVal = $('#search_genre_input').val();
        if(!genreVal){
            if(this.movieList){
                if(callback) callback();
            }else{
                this.movieList = new MovieCollection([],{genre:genreVal});
                this.movieList.fetch({success: function(){
                    $('#sidebar').html(new MovieListView({model: app.movieList}).render().el)
                }});
            }
        }else{
            if(this.searchList){
                if(callback) callback();
            }else{
                this.searchList = new MovieCollection([],{genre:genreVal});
                this.searchList.fetch({success: function(){
                    $('#sidebar').html(new MovieListView({model: app.searchList}).render().el)
                }});
            }
        }
    }
});

tpl.loadTemplates(['header','movie-details','movie-list-item'],function(){
    app = new Approuter();
    Backbone.history.start();
});
