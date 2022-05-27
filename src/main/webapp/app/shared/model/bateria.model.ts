export interface IBateria {
  id?: number;
  numeroSerie?: string | null;
  status?: string | null;
  carga?: number | null;
}

export const defaultValue: Readonly<IBateria> = {};
