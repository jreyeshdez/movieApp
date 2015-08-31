tpl = {
    templates: {}, // Hash of templates.
    /* It loads recursively all the templates for the app. */
    loadTemplates: function(names,callback){
        var that = this;
        var loadTemplate = function(index){
            var name=names[index];
            console.log('Loading template: '+name);
            $.get('tpl/'+name+'.html',function(data){
                that.templates[name]=data;
                index++;
                index < names.length ? loadTemplate(index) : callback();

            });
        }
        loadTemplate(0);
    },
    /* Will get the template by name from the hash of templates. */
    get: function(name){
        return this.templates[name];
    }
};