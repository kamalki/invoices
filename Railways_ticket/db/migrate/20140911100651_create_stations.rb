class CreateStations < ActiveRecord::Migration
  def change
    create_table :stations do |t|
      t.integer :stationid_id
      t.string :station_name

      t.timestamps
    end
  end
end
