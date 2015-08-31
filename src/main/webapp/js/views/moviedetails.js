window.MovieView = Backbone.View.extend({
    tagName: "div",

    initialize: function(){
        this.template = _.template(tpl.get('movie-details'));
        this.model.bind("change",this.render,this);
    },

    render: function(eventName){
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    },

    /* Backbone events hash allows us to attach event
    *  listeners to either el-relative custom selectors
    *  or directly to el if no selector is provided.
    *  'eventName selector' : 'callBackFunction' and a number
    *  of DOM event-types are supported, including click, submit, mouseover, etc
    * */
    events: {
        "change input" : "change",
        "click .save" : "saveMovie",
        "click .delete" : "deleteMovie"
    },

    change: function(event){
        var target = event.target;
        console.log('changing ' + target.id + ' from: ' + target.defaultValue + ' to: ' + target.value);
    },

    /* Creating a model will trigger an 'add' event on
    *  the collection, a 'request' event as the new model
    *  is sent to the server as well as a 'sync' event.
    * */
    saveMovie: function(){
        this.model.set({
            title: $('#title').val(),
            year: $('#year').val(),
            description: $('#description').val(),
            genres: parseGenres()
        });
        if(this.model.isNew()){
            var self = this;
            app.movieList.create(this.model,{   //REST calling
                success: function(){
                    app.navigate('movies/'+self.model.id,false);
                }
            });
        }else{
            /* It saves the model to database by delegating to Backbone.sync */
            this.model.save();
        }
        app.navigate("movies",true);
        return false;
    },
    /* Destroys the model on the server by delegating
    *  an HTTP DELETE request to Backbone.sync.*/
    deleteMovie: function(){
        this.model.destroy({
            success: function(){
                alert('Movie delete successfully');
                window.history.back();
            }
        });
        app.navigate("movies",true);
        return false;
    }
});
/* Prepare genres before being sent */
function parseGenres(){
    var json = [];
    var toSplit = $('#genres').val().split(",");
    for(var i=0;i<toSplit.length;i++){
        json.push(toSplit[i]);
    }
    return json;
}