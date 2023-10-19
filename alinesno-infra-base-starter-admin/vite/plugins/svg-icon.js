import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'
import path from 'path'

export default function createSvgIcon(isBuild) {
 
 	const infraUiPath = path.resolve("node_modules" , "alinesno-infra-ui")

 	console.log("[require.resolve('alinesno-infra-ui') 'src/assets/icons/svg')] = " + path.resolve(infraUiPath, 'assets/icons/svg'))

    return createSvgIconsPlugin({
		iconDirs: [path.resolve(infraUiPath, 'assets/icons/svg')],
        symbolId: 'icon-[dir]-[name]',
        svgoOptions: isBuild
    })
}
