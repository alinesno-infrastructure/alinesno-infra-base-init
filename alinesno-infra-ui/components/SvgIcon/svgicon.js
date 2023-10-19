import * as components from '@element-plus/icons-vue'

export default {
    install: (app) => {
        for (const key in components) {

        	console.log('icons vue = ' + key)

            const componentConfig = components[key];
            app.component(componentConfig.name, componentConfig);
        }
    },
};
