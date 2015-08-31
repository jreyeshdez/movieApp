window.HeaderView = Backbone.View.extend({
    initialize: function(){
        this.template = _.template(tpl.get('header'));
    },

    render: function(eventName){
        $(this.el).html(this.template());
        return this;
    },
    /* Listening for an event. It will call the mapped function */
    events: {
        "click .new" : "newMovie",
        "click .searchGenre" : "searchByGenre",
        "click .back" : "back"
    },

    newMovie: function(event){
        app.navigate("movies/new",true);
        return false;
    },

    searchByGenre: function(event){
        app.navigate("movies/searchGenre",true);
        return false;
    },

    back: function(event){
        app.navigate("",true);
        return false;
    }
});