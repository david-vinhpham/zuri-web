import express from 'express';

import serverRenderer from './middleware/renderer';

const PORT = 3000;
const path = require('path');

// initialize the application and create the routes
const app = express();
const router = express.Router();

// root (/) should always serve our server rendered page
router.use('^/$', serverRenderer);

// other static resources
router.use(express.static(path.resolve(__dirname, '..', 'build'), { maxAge: '30d' }));

// anything else should act as our index page
// react-router will take care of everything
router.use('*', serverRenderer);

// tell the app to user the above rules
app.use(router);

// start the app
app.listen(PORT, error => {
    if (error) {
        return console.log('Something bad happened', error);
    }
    console.log(`listening on ${PORT} ...`);
});
