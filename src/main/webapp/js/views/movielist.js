window.MovieListView = Backbone.View.extend({
    tagName: 'ul',

    initialize: function(){
        this.model.bind("reset",this.render,this);
        var self = this;
        this.model.bind("add",function(movie){
            $(self.el).append(new MovieListItemView({model: movie}).render().el);
        });
    },

    /* Override view default render function
    *  that will render the view template from
    *  model data and will update this.el with new HTML
    * */
    render: function(eventName){
        /* Underscore function _.each() */
        _.each(this.model.models,function(movie){
            /* All views have a DOM element at all times
            *  which can be accessed by this.el
            * */
            $(this.el).append(new MovieListItemView({model: movie}).render().el)
        },this);
        return this;
    }
});

window.MovieListItemView = Backbone.View.extend({
    tagName: "li",

    initialize: function(){
        this.template = _.template(tpl.get('movie-list-item'));
        this.model.bind("change", this.render, this);
        this.model.bind("destroy", this.close, this);
    },

    render: function(eventName){
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }
});