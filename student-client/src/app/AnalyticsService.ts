import { environment } from './../environments/environment.prod';
import { ScriptInjectorService } from './ScriptInjectorService';

declare const window: Window;
declare const _satellite: any;

@Injectable({
  providedIn: 'root',
})
export class AnalyticsService {
  constructor(private ScriptInjectorService: ScriptInjectorService) {}
  initAdobeLaunchDataLayer() {
    (window as Window).digitalData = any;
  }
  async injectAdobeLaunchScript() {
    try {
      await this.ScriptInjectorService.load(
        'launch',
        environment.ADOBE_LAUNCH_SCRIPT_URL
      );
      _satellite.pageBottom();
    } catch (e) {
      console.error('Error While loading Adobe Launch script', e);
    }
  }

  setPageId(pageId: string) {
    (window as Window).digitalData.page.pageInfo.pageId = pageId;
  }
}
